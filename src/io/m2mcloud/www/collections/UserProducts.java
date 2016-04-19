package io.m2mcloud.www.collections;

import java.util.Date;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class UserProducts {

	private String productId;
	private String role;
	private Date productCreateTime;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getProductCreateTime() {
		return productCreateTime;
	}
	public void setProductCreateTime(Date productCreateTime) {
		this.productCreateTime = productCreateTime;
	}
	
}
