package io.m2mcloud.www.users;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetUser {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Users users;
	public String getUserInfo(String tokenId){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户还未注册";
		}
		else{
			db.Disconnect();
			return "获取用户信息成功";
		}
	}
	
	public Users getUsers() {
		return users;
	}
	
}
