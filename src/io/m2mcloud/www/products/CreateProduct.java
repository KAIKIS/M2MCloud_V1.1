package io.m2mcloud.www.products;

import java.util.Date;

import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;

public class CreateProduct {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Products products;
	public String insertProduct(String tokenId, String productName, String description){
		
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
		else{//需要在products集合中插入后，在users集合中同时进行插入
			Date date = new Date();
			products = new Products();
			String productId = MD5.GetMD5Code(tokenId + date.toString());//使用tokenId和时间混合
			String secret = MD5.GetMD5Code(date.toString());

			products.setProductId(productId);
			products.setSecret(secret);
			products.setProductName(productName);
			products.setDescription(description);
			products.setCreateTime(date);
			products.setOnline(0);
			products.setTotal(0);
			datastore.save(products);
			
			UserProducts userProducts = new UserProducts();
			userProducts.setProductId(productId);
			userProducts.setRole("admin");
			userProducts.setProductCreateTime(date);
			users.getProducts().add(userProducts);
			datastore.save(users);
			
			db.Disconnect();
			return "产品创建成功";
		}
	}
	
	public Products getProducts() {
		return products;
	}
	
	
}
