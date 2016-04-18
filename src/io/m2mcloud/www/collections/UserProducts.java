package io.m2mcloud.www.collections;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class UserProducts {

	private String productId;
	private String role;
	
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
}
