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
	
}
