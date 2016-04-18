package io.m2mcloud.www.collections;

<<<<<<< HEAD
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class UserProducts {

=======
import org.mongodb.morphia.annotations.Reference;

public class UserProducts {

	@Reference
>>>>>>> origin/master
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
