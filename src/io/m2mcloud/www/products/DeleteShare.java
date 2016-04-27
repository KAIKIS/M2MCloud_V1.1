package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
/**
 * 只有管理员才能删除
 * @author Kai
 *
 */
public class DeleteShare {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId, String shareName){
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户无权删除";
		}
		else{//判断分享者存不存在
			Users share = datastore.createQuery(Users.class)
					.field("userName").equal(shareName)
					.field("products.productId").equal(productId).get();
			if(share == null){
				db.Disconnect();
				return "分享者不存在";
			}
			else{//判断该分享者有没有被分享该产品
				int size = share.getProducts().size();
				for (int i = 0; i < size; i++) {
					if(!share.getProducts().get(i).getProductId().equals(productId)){
						db.Disconnect();
						return "该分享者还没有被分享";
					}
				}
				UserProducts up = new UserProducts();
				up.setProductId(productId);
				up.setRole("share");
				Query<Users> queryProducts = datastore.createQuery(Users.class)
						.field("products")
						.hasThisElement(up);
				UpdateOperations<Users> updateProducts = datastore.createUpdateOperations(Users.class)
															.removeAll("products", up);
				UpdateResults results = datastore.update(queryProducts, updateProducts);
				db.Disconnect();
				return "删除分享者成功";
			}
		}
		
	}
}
