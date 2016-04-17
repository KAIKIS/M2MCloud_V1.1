package io.m2mcloud.www.collections;

import io.m2mcloud.www.collections.UserProducts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

@Entity(value = "users", noClassnameStored = true)
public class Users {

	@Id
	private ObjectId id;
	private String tokenId;
	
	@Indexed(options = @IndexOptions(unique = true))
	private String userName;
	private Date createTime;
	
	@Embedded("products")
	private List<UserProducts> products = new ArrayList<UserProducts>();
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<UserProducts> getProducts() {
		return products;
	}
	public void setProducts(List<UserProducts> products) {
		this.products = products;
	}
	
}
