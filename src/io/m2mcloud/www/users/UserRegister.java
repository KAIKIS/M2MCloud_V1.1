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
			return "�������ݿ�ʧ��";
		}
//���벻��������
		datastore = db.getDatastore();
		String tokenId = MD5.GetMD5Code(userName + userPwd);
		try{
		users = new Users();
		users.setId(new ObjectId());
		users.setTokenId(tokenId);
		users.setUserName(userName);
		users.setCreateTime(new Date());
		datastore.save(users);
		}catch(com.mongodb.DuplicateKeyException e){
			System.out.println(e);
			db.Disconnect();
			return "�û��Ѿ�����";
		}	
		
		db.Disconnect();
		return "�û�ע��ɹ�";

	}

	public Users getUsers() {
		return users;
	}
	
	
	
}
