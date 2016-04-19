package io.m2mcloud.www.users;

import org.mongodb.morphia.Datastore;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

public class UserLogin {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Users users;
	
	public String findUser(String userName, String userPwd) {
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		String tokenId = MD5.GetMD5Code(userName + userPwd);
		users = datastore.createQuery(Users.class)
						.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户还未注册";
		}
		else{
			db.Disconnect();
			return "用户登录成功";
		}
	}

	public Users getUsers() {
		return users;
	}
	
}
