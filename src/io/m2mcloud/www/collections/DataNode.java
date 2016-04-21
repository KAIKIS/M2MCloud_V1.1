package io.m2mcloud.www.collections;

import java.util.Date;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

@Embedded
public class DataNode {
	
	private String nodeId;
	private String nodeName;
	private String nodeType;
	private String units;
	private String nodeDescription;
	private Boolean store;
	private Date nodeCreateTime;
	private String alarmName;
	private String alarmType;
	private Object threshold0;
	private Object threshold1;
	private Date alarmCreateTime;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getNodeDescription() {
		return nodeDescription;
	}
	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}
	public Boolean getStore() {
		return store;
	}
	public void setStore(Boolean store) {
		this.store = store;
	}
	public Date getNodeCreateTime() {
		return nodeCreateTime;
	}
	public void setNodeCreateTime(Date nodeCreateTime) {
		this.nodeCreateTime = nodeCreateTime;
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
	public Object getThreshold0() {
		return threshold0;
	}
	public void setThreshold0(Object threshold0) {
		this.threshold0 = threshold0;
	}
	public Object getThreshold1() {
		return threshold1;
	}
	public void setThreshold1(Object threshold1) {
		this.threshold1 = threshold1;
	}
	public Date getAlarmCreateTime() {
		return alarmCreateTime;
	}
	public void setAlarmCreateTime(Date alarmCreateTime) {
		this.alarmCreateTime = alarmCreateTime;
	}
}
