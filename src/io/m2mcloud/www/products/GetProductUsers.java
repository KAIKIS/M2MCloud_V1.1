package io.m2mcloud.www.products;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 所有用户都能使用
 * @author Kai
 *
 */
public class GetProductUsers {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<String> userNames = new ArrayList<String>();
	private List<UserProducts> userProducts = new ArrayList<UserProducts>();
	
	public String getPU(String tokenId, String productId){
		
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
			List<Users> queryUsers = datastore.createQuery(Users.class)
					.field("products.productId").equal(productId).asList();
			int size = queryUsers.size();
			for (int i = 0; i < size; i++) {
				userNames.add(queryUsers.get(i).getUserName());
				userProducts.addAll((queryUsers.get(i).getProducts()));
			}
			for (int i = 0; i < userProducts.size(); i++) {
				if(!userProducts.get(i).getProductId().equals(productId)){
					userProducts.remove(i--);
				}
			}
			db.Disconnect();
			return "获取产品下的用户成功";
		}
		
	}

	public List<String> getUserNames() {
		return userNames;
	}

	public List<UserProducts> getUserProducts() {
		return userProducts;
	}
	
}
