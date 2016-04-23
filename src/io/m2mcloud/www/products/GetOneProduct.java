package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetOneProduct {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Products products;
	
	public String getOP(String tokenId, String productId){
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
			products = datastore.createQuery(Products.class)
							.field("productId").equal(productId).get();
			if(products == null){
				db.Disconnect();
				return "该产品不存在";
			}
			else{
				db.Disconnect();
				return "获取产品成功";
			}
			
		}
	}

	public Products getProducts() {
		return products;
	}
	
}
