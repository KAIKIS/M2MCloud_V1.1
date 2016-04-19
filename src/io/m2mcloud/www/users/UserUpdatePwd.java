package io.m2mcloud.www.users;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

public class UserUpdatePwd {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Users users;
	
	public String UpdatePwd(String tokenId, String userName, String userOldPwd, String userNewPwd){
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		String oldTokenId = MD5.GetMD5Code(userName + userOldPwd);
		if(!oldTokenId.equals(tokenId)){
			return "用户输入的用户名或密码错误";
		}
		else{
			datastore = db.getDatastore();
			users = datastore.createQuery(Users.class)
					.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
			if(users == null){
				db.Disconnect();
				return "用户还未注册";
			}
			else{
				String newTokenId = MD5.GetMD5Code(userName + userNewPwd);
				Query<Users> queryUser = datastore.createQuery(Users.class)
												.field("tokenId").equal(tokenId);
				UpdateOperations<Users> updateUser = datastore.createUpdateOperations(Users.class)
																	.set("tokenId", newTokenId);
				UpdateResults results = datastore.update(queryUser, updateUser);
				db.Disconnect();
				return "用户修改密码成功";
			}
		}
	}
	
}
