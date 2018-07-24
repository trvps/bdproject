package com.htdz.def.dbmodel;


// Generated 2018-4-25 17:07:31 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tdevice generated by hbm2java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tdevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String deviceSn;
	private String trackerSim;
	private String headPortrait;
	private String remark;
	private String contact;
	private String mobile1;
	private String mobile2;
	private String mobile3;
	private String firmware;
	private String password;
	private Date createTime;
	private Date expiredTime;
	private Date expiredTimeDe;
	private String connCountry;
	private Integer connid;
	private Date connLastTime;
	private Integer defensive;
	private Integer productType;
	private Integer hasSwitch;
	private Integer orgId;
	private Integer ranges;
	private String hardware;
	private Date collectDatetime;
	private Date rcvTime;
	private Double lat;
	private Double lng;
	private Float speed;
	private Float direction;
	private Short satelliteNum;
	private String gpsFlag;
	private String battery;
	private Integer steps;
	private String flag;
	private Integer gpsInterval;
	private String nickname;
	private Date firstGpsDatetime;
	private Double firstLat;
	private Double firstLng;
	private Character fisrtGpsFlag;
	private Date fisrtRcvTime;
	private String deviceImei;
	private Date firstBindDate;
	private Integer isLock;
	private Integer oldDid;
	private Integer aroundRanges;
	private String insurCode;
	private Integer disable;
	private String centerMobile;
	private Integer language;
	private Integer protocolType;
	private Integer timezone;
	private Integer timezoneid;
	private Integer lincesId;
	private Integer dataSource;
	private Integer step;
	private Integer profile;
	private Integer isLbs;


	public Tdevice(String deviceSn, Integer productType, Integer protocolType,
			Integer timezone, Integer timezoneid) {
		setDeviceSn(deviceSn);
		setProductType(productType);
		setProtocolType(protocolType);
		setTimezone(timezone);
		setTimezoneid(timezoneid);
	}

	public Tdevice(String deviceSn, String trackerSim, String headPortrait,
			String remark, String contact, String mobile1, String mobile2,
			String mobile3, String firmware, String password, Date createTime,
			Date expiredTime, Date expiredTimeDe, String connCountry,
			Integer connid, Date connLastTime, Integer defensive,
			Integer productType, Integer hasSwitch, Integer orgId,
			Integer ranges, String hardware, Date collectDatetime,
			Date rcvTime, Double lat, Double lng, Float speed, Float direction,
			Short satelliteNum, String gpsFlag, String battery, Integer steps,
			String flag, Integer gpsInterval, String nickname,
			Date firstGpsDatetime, Double firstLat, Double firstLng,
			Character fisrtGpsFlag, Date fisrtRcvTime, String deviceImei,
			Date firstBindDate, Integer isLock, Integer oldDid,
			Integer aroundRanges, String insurCode, Integer disable,
			String centerMobile, Integer language, Integer protocolType,
			Integer timezone, Integer timezoneid, Integer lincesId,
			Integer dataSource, Integer step, Integer profile, Integer isLbs) {
		setDeviceSn(deviceSn);
		setTrackerSim(trackerSim);
		setHeadPortrait(headPortrait);
		setRemark(remark);
		setContact(contact);
		setMobile1(mobile1);
		setMobile2(mobile2);
		setMobile3(mobile3);
		setFirmware(firmware);
		setPassword(password);
		setCreateTime(createTime);
		setExpiredTime(expiredTime);
		setExpiredTimeDe(expiredTimeDe);
		setConnCountry(connCountry);
		setConnid(connid);
		setConnLastTime(connLastTime);
		setDefensive(defensive);
		setProductType(productType);
		setHasSwitch(hasSwitch);
		setOrgId(orgId);
		setRanges(ranges);
		setHardware(hardware);
		setCollectDatetime(collectDatetime);
		setRcvTime(rcvTime);
		setLat(lat);
		setLng(lng);
		setSpeed(speed);
		setDirection(direction);
		setSatelliteNum(satelliteNum);
		setGpsFlag(gpsFlag);
		setBattery(battery);
		setSteps(steps);
		setFlag(flag);
		setGpsInterval(gpsInterval);
		setNickname(nickname);
		setFirstGpsDatetime(firstGpsDatetime);
		setFirstLat(firstLat);
		setFirstLng(firstLng);
		setFisrtGpsFlag(fisrtGpsFlag);
		setFisrtRcvTime(fisrtRcvTime);
		setDeviceImei(deviceImei);
		setFirstBindDate(firstBindDate);
		setIsLock(isLock);
		setOldDid(oldDid);
		setAroundRanges(aroundRanges);
		setInsurCode(insurCode);
		setDisable(disable);
		setCenterMobile(centerMobile);
		setLanguage(language);
		setProtocolType(protocolType);
		setTimezone(timezone);
		setTimezoneid(timezoneid);
		setLincesId(lincesId);
		setDataSource(dataSource);
		setStep(step);
		setProfile(profile);
		setIsLbs(isLbs);
	}


}