package io.m2mcloud.www.users;

import java.util.Date;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

public class UserRegister {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Users users;
	
	public String insertUser(String userName, String userPwd){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		String tokenId = MD5.GetMD5Code(userName + userPwd);
		users = datastore.createQuery(Users.class)
						.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			users.setId(new ObjectId());
			users.setTokenId(tokenId);
			users.setUserName(userName);
			users.setCreateTime(new Date());
			datastore.save(users);
			db.Disconnect();
			return "用户注册成功";
		}
		else{
			db.Disconnect();
			return "用户已经存在";
		}
		
		
	}
	
}
