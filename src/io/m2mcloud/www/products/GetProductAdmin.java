package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetProductAdmin {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private String adminName;
	
	public String getPA(String tokenId, String productId){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户还未注册";
		}
		else{
			Users admin = datastore.createQuery(Users.class)
								.field("products.productId").equal(productId)
								.field("products.role").equal("admin").get();
			adminName = admin.getUserName();
			db.Disconnect();
			return "获取产品管理员成功";
		}
	}

	public String getAdminName() {
		return adminName;
	}
	
}
