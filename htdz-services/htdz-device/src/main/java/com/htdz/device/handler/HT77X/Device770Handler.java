package com.htdz.device.handler.HT77X;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htdz.common.Consts;
import com.htdz.common.LogManager;
import com.htdz.common.utils.DataUtil;
import com.htdz.db.service.DeviceService;
import com.htdz.db.service.DeviceSessionMapService;
import com.htdz.db.service.GpsDataLastService;
import com.htdz.db.service.TDeviceUserService;
import com.htdz.db.service.UserService;
import com.htdz.def.data.RPCResult;
import com.htdz.def.data.RouteInfo;
import com.htdz.def.dbmodel.DeviceSessionMap;
import com.htdz.def.dbmodel.TDeviceUser;
import com.htdz.def.dbmodel.Tdevice;
import com.htdz.def.view.PushInfo;
import com.htdz.def.view.UserConn;
import com.htdz.device.data.DeviceSessionInfo;
import com.htdz.device.data.Message77X;
import com.htdz.device.dubbo.TaskServiceConsumer;
import com.htdz.device.handler.DeviceBaseHandler;
import com.htdz.device.handler.DevicePackageHandler;

@Component
public class Device770Handler implements DeviceBaseHandler,
		DevicePackageHandler {
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceSessionMapService deviceSessionMapService;

	@Autowired
	private Device77XProtocol device77XProtocol;

	@Autowired
	private TDeviceUserService tDeviceUserService;

	@Autowired
	UserService userService;

	@Autowired
	TaskServiceConsumer taskServiceConsumer;

	@Autowired
	GpsDataLastService gpsDataLastService;

	private ConcurrentMap<String, DeviceSessionInfo> dsiMap = new ConcurrentHashMap<String, DeviceSessionInfo>();
	
	private ExecutorService executor = Executors.newFixedThreadPool(20);
	

	@Override
	public void scheduledWork() {
		// 清理超时无效数据
		Iterator<Entry<String, DeviceSessionInfo>> iterator = dsiMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, DeviceSessionInfo> entry = iterator.next();
			DeviceSessionInfo dsi = entry.getValue();
			if (dsi.isTimeout()) {
				LogManager.info("清理超时无效数据: deviceName={} deviceSession={}",
						getDeviceName(), dsi.getKey());
				iterator.remove();
			}
		}
	}

	public String getDeviceName() {
		return Consts.Device_HT770;
	}
	
	public ExecutorService getExecutorService() {
		return executor;
	}
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		DeviceBaseHandler.super.onApplicationEvent(event);
		
		device77XProtocol.setExecutorService(executor);
	}

	@Override
	public void handleDeviceRegisted(RouteInfo ri, String deviceName,
			String deviceSession) {
		DeviceSessionInfo dsi = new DeviceSessionInfo();
		dsi.setKey(deviceSession);
		dsiMap.put(deviceSession, dsi);
	}

	@Override
	public void handleDeviceUnregisted(RouteInfo ri, String deviceName,
			String deviceSession) {
		executor.execute(new Runnable() {
			public void run() {
				dsiMap.remove(deviceSession);

				DeviceSessionMap deviceSessionMap = deviceSessionMapService
						.getBySessionId(deviceSession);
				if (deviceSessionMap != null && deviceSessionMap.getDevice_sn() != null) {
					String deviceSn = deviceSessionMap.getDevice_sn();
					if (deviceSn != null) {
						Device77XProtocol.deviceMap.remove(deviceSn);
						Device77XProtocol.gpsDataMap.remove(deviceSn);
					}

					// 推送在线状态(下线)
					LogManager.info("设备:{}下线，推送在线状态", deviceSn);
					PushInfo pushInfo = new PushInfo();
					pushInfo.setEquipId(deviceSn);
					pushInfo.setOnlinestatus("0");
					pushInfo.setMsgType(2);

					List<UserConn> userConnList = userService
							.getUserDeviceInfo(pushInfo.getEquipId());
					if (null != userConnList && userConnList.size() > 0) {
						for (UserConn userConn : userConnList) {
							if (StringUtils.isEmpty(userConn.getToken())) // 只推送安卓
							{
								RPCResult pushRet = taskServiceConsumer.pushMsg(
										pushInfo, userConn);
								LogManager.info("设备:{}下线，推送在线状态:{}", deviceSn,
										pushRet.getRpcErrCode() == 0 ? "成功" : "失败");
							}
						}
					}

					// 跟新最后位置表的在线状态
					gpsDataLastService.updateOnlineStatus(deviceSn, 0);

					// 删除deviceSessionMap表数据
					deviceSessionMapService.delete(deviceSession);
				}
			}
		});
	}

	/**
	 * 收到网关转发的数据，识别出完整的指令包
	 */
	@Override
	public RPCResult handleDeviceMessage(RouteInfo ri, String deviceName,
			String deviceSession, byte[] data) {
		if (data.length < Consts.LOG_MAX_DATASIZE) {
			LogManager.info(
					"收到网关转发的设备数据：deviceName={} deviceSession={} data={}",
					deviceName, deviceSession, DataUtil.bytesToString(data));
		} else {
			LogManager.info(
					"收到网关转发的设备数据：deviceName={} deviceSession={} data len={}",
					deviceName, deviceSession, data.length);
		}

		DeviceSessionInfo dsi = dsiMap.get(deviceSession);
		if (dsi == null) {
			dsi = new DeviceSessionInfo();
			dsi.setKey(deviceSession);
			dsiMap.put(deviceSession, dsi);
		}

		RPCResult result = Message77X.packageCheck(deviceName, deviceSession,
				data, dsi, this);
		return result;
	}

	/**
	 * 处理完整的设备指令
	 * 
	 * @param deviceName
	 * @param deviceSession
	 * @param data
	 * @return
	 */
	@Override
	public RPCResult handleDevicePackage(String deviceName,
			String deviceSession, byte[] data) {
		if (data.length < Consts.LOG_MAX_DATASIZE) {
			LogManager.info("处理设备数据：deviceName={} deviceSession={} data={}",
					deviceName, deviceSession, DataUtil.bytesToString(data));
		} else {
			LogManager.info(
					"处理设备数据：deviceName={} deviceSession={} data len={}",
					deviceName, deviceSession, data.length);
		}

		Message77X msg = Message77X.fromPackage(data);
		if (msg == null) {
			LogManager.info("设备 deviceName={} deviceSession={} 数据错误, 拒绝服务",
					deviceName, deviceSession);
			return RPCResult.serviceRefuse();
		}

		try {
			if (data.length > Consts.LOG_MAX_DATASIZE) {
				LogManager
						.info("处理设备数据 deviceName={} deviceSession={} factoryName={} deviceSn={} datalen={} method={}",
								deviceName, deviceSession,
								msg.getFactoryName(), msg.getDeviceSn(),
								msg.getDatalen(), msg.getMethod());
			}
			
			// 设备不存在，拒接服务
			Tdevice tdevice = deviceService.select(msg.getDeviceSn());
			if (tdevice == null) {
				LogManager.info(
						"设备 deviceName={} deviceSession={} 拒绝服务，设备{}不存在",
						deviceName, deviceSession, msg.getDeviceSn());
				return RPCResult.serviceRefuse();
			} else {
				Date ExpiredDate = tdevice.getExpiredTime();
				Date CurDate = new Date();
				if (ExpiredDate.compareTo(CurDate) < 0) {
					LogManager.info(
							"设备 deviceName={} deviceSession={} 拒绝服务，设备{}服务到期",
							deviceName, deviceSession, msg.getDeviceSn());
					return RPCResult.serviceRefuse();
				}
			}

			// 根据设备号判断当前设备是否绑定，没绑定就断开链接
			List<TDeviceUser> list = tDeviceUserService.getByDeviceSn(msg.getDeviceSn());
			if (list == null || list.size() < 1) {
				LogManager.info(
						"设备 deviceName={} deviceSession={} 拒绝服务，设备{}未绑定",
						deviceName, deviceSession, msg.getDeviceSn());
				return RPCResult.serviceRefuse();
			}

			// 供反射调用的参数
			Object[] objParam = new Object[3];
			objParam[0] = deviceSession;
			objParam[1] = msg;
			objParam[2] = deviceName;

			Method invokeMethod = Device77XProtocol.class.getMethod("call_"
					+ msg.getMethod().toUpperCase(), String.class,
					Message77X.class, String.class);

			// 调用业务逻辑方法
			RPCResult result = (RPCResult) invokeMethod.invoke(
					device77XProtocol, objParam);
			return result;
		} catch (NoSuchMethodException e) {
			LogManager.info("{} 方法不存在", "call_" + msg.getMethod());
			return RPCResult.success();
		} catch (Exception e) {
			LogManager.exception(e.getMessage(), e);

			return RPCResult.failed();
		}
	}

	@Override
	public RPCResult handleWebMessage(RouteInfo ri, String path,
			String deviceName, String deviceSn, Map<String, String[]> params) {
		try {
			String[] pathArray = path.split("\\/");
			String method = pathArray[1].toLowerCase();
			// 供反射调用的参数
			Object[] objParam = new Object[3];
			objParam[0] = deviceSn;
			objParam[1] = deviceName;
			objParam[2] = params;

			Method invokeMethod = Device77XProtocol.class.getMethod(method,
					String.class, String.class, Map.class);
			// 调用业务逻辑方法
			RPCResult result = (RPCResult) invokeMethod.invoke(
					device77XProtocol, objParam);
			
			LogManager.info(result.toString());
			
			return result;
		} catch (Exception e) {
			LogManager.exception(e.getMessage(), e);

			RPCResult ret = RPCResult.serviceRefuse();
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "3");
			ret.setRpcResult(JSON.toJSONString(map,
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteMapNullValue,
					SerializerFeature.DisableCheckSpecialChar));
			
			return ret;
		}
	}
}