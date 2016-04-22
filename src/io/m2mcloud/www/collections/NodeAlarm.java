package io.m2mcloud.www.collections;

import java.util.Date;

import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class NodeAlarm {
	
	private String nodeId;
	private String alarmName;
	private String alarmType;
	private double threshold0;
	private double threshold1;
	private Date alarmCreateTime;
	

	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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
