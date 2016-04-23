package io.m2mcloud.www.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersTokenResult {
	private String userName;
	private Date userCreateTime;
	private String result;
	private List<ProductResult> productResult = new ArrayList<ProductResult>();

	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<ProductResult> getProductResult() {
		return productResult;
	}
	public void setProductResult(List<ProductResult> productResult) {
		this.productResult = productResult;
	}	
}
