package io.m2mcloud.www.collections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "devices", noClassnameStored = true)
public class Devices {

	@Id
	private String deviceId;
	private String mac;
	private String productId;
	private String deviceName;
	private String address;
	private String clientIp;
	private String longitude;
	private String latitude;
	private Date createTime;
	private Boolean online;
	@Embedded
	private List<DataNode> node = new ArrayList<DataNode>();
	@Embedded
	private List<NodeAlarm> alarm = new ArrayList<NodeAlarm>();
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public List<DataNode> getNode() {
		return node;
	}
	public void setNode(List<DataNode> node) {
		this.node = node;
	}
	public List<NodeAlarm> getAlarm() {
		return alarm;
	}
	public void setAlarm(List<NodeAlarm> alarm) {
		this.alarm = alarm;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}
	
}
