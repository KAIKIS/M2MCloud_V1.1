package io.m2mcloud.www.collections;

import java.util.Date;

<<<<<<< HEAD
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

=======
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


>>>>>>> origin/master
@Entity(value = "products", noClassnameStored = true)
public class Products {

	@Id
<<<<<<< HEAD
=======
	private ObjectId objectId;
>>>>>>> origin/master
	private String productId;
	private String secret;
	private String productName;
	private String description;
	private Date createTime;
	private int online;
	private int total;
	
<<<<<<< HEAD
=======
	
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
>>>>>>> origin/master
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
<<<<<<< HEAD
=======
	
	
	
>>>>>>> origin/master
}
