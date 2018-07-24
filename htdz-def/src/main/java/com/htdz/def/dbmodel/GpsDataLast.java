package com.htdz.def.dbmodel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpsDataLast implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String deviceSn;
	private Date collectDatetime;
	private Date rcvTime;
	private Double lat;
	private Double lng;
	private Float speed;
	private Float direction;
	private Integer satelliteNum;
	private Integer locationId;
	private Integer cellId;
	private String msgId;
	private String gpsFlag;
	private String flag;
	private String battery;
	private Integer steps;
	private Integer addrStatus;
	private Integer addrStatusBak;
	private Date cpyDate;
	private Integer lbsWifiRange;
	private Character lbsWifiFlag;
	private Character accStatus;
	private Date lastAcconTime;
	private Date deviceUtcTime;
	private Long totalTripMileage;
	private Integer currentTripMileage;
	private Double totalFuel;
	private Double currentFuel;
	private Integer engineTpye;
	private Integer obdrequestSuccPercent;
	private Integer obdrequestErrorTimes;
	private Integer hardWareType;
	private Integer commType;
	private Integer sinalStrong;
	private Integer commError;
	private Character obdConn;
	private Character engineRotation;
	private Character gpsstatus;
	private Character rtcstatus;
	private Character voltageStatus;
	private Character flashStatus;
	private Character sysParaStatus;
	private Character calMileageType;
	private Character flashHaveHistoryData;
	private Integer rpm;
	private Integer altitude;
	private Integer longitudeWay;
	private Integer latitudeWay;
	private Integer deleteflag;
	private Double calorie;
	private Integer online;
	private Integer carStatus;
}
