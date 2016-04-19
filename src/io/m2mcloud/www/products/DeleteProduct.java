package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 * 删除指定的产品，只有管理员才可以删除
 * @author Kai
 *
 */
public class DeleteProduct {
	
	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId){
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//查找这个用户是否拥有这个产品且是否是管理员 
		
		if(users == null){
			db.Disconnect();
			return "用户无法删除产品";
		}
		else{
			//删除Users下的products信息
			UserProducts up = new UserProducts();
			up.setProductId(productId);
			up.setRole("admin");
			Query<Users> queryProducts = datastore.createQuery(Users.class)
					.field("products")
					.hasThisElement(up);
			UpdateOperations<Users> updateProducts = datastore.createUpdateOperations(Users.class)
					.removeAll("products", up);

			UpdateResults results = datastore.update(queryProducts, updateProducts);
			
			//删除Devices下的productId信息
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
												.field("productId").equal(productId);
			datastore.delete(queryDevice);
			
			//删除Products信息
			Query<Products> queryProduct = datastore.createQuery(Products.class)
												.field("productId").equal(productId);
			datastore.delete(queryProduct);
			
			//删除设备信息下的数据信息
			
			
			db.Disconnect();
			return "删除产品成功";
			
		}
		
		
	}
	
}
