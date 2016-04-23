package io.m2mcloud.www.result;

import java.util.Date;

public class ProductResult {
	
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
