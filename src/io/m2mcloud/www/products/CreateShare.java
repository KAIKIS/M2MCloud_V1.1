package io.m2mcloud.www.products;

import java.util.Date;

import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

/**
 * 只有管理员才能创建
 * @author Kai
 *
 */
public class CreateShare {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String insertShare(String tokenId, String productId, String shareName){
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();
		
		if(users == null){
			db.Disconnect();
			return "用户无权创建分享者";
		}
		else{
			Users share = datastore.createQuery(Users.class)
					.field("userName").equal(shareName).get();
			if(share == null){
				db.Disconnect();
				return "分享者不存在";
			}
			else{
				int size = share.getProducts().size();
				for (int i = 0; i < size; i++) {
					if(share.getProducts().get(i).getProductId().equals(productId)){
						db.Disconnect();
						return "该分享者已经被分享";
					}
				}
				Date date = new Date();
				UserProducts userProducts = new UserProducts();
				userProducts.setProductId(productId);
				userProducts.setRole("share");
				userProducts.setProductCreateTime(date);
				
				share.getProducts().add(userProducts);
				datastore.save(share);
				db.Disconnect();
				return "分享成功";
			}
			
		}
		
	}
}
