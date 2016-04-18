package io.m2mcloud.www.collections;

import java.util.Date;

public class DeviceData {

	private String dataName;
	private String dataType;
	private String units;
	private String dataDescription;
	private Boolean store;
	private Date dataCreateTime;
	private String alarmName;
	private String alarmType;
	private double threshold0;
	private double threshold1;
	private Date alarmCreateTime;
	
	
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getDataDescription() {
		return dataDescription;
	}
	public void setDataDescription(String dataDescription) {
		this.dataDescription = dataDescription;
	}
	public Boolean getStore() {
		return store;
	}
	public void setStore(Boolean store) {
		this.store = store;
	}
	public Date getDataCreateTime() {
		return dataCreateTime;
	}
	public void setDataCreateTime(Date dataCreateTime) {
		this.dataCreateTime = dataCreateTime;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public double getThreshold0() {
		return threshold0;
	}
	public void setThreshold0(double threshold0) {
		this.threshold0 = threshold0;
	}
	public double getThreshold1() {
		return threshold1;
	}
	public void setThreshold1(double threshold1) {
		this.threshold1 = threshold1;
	}
	public Date getAlarmCreateTime() {
		return alarmCreateTime;
	}
	public void setAlarmCreateTime(Date alarmCreateTime) {
		this.alarmCreateTime = alarmCreateTime;
	}
	
}
