package com.htdz.device.handler.HT77X;


import org.springframework.stereotype.Component;
import com.htdz.common.Consts;


@Component
public class Device771Handler extends Device770Handler {
	public String getDeviceName() {
		return Consts.Device_HT771;
	}
}
