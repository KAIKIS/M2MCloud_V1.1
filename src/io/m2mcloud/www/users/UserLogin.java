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
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		String tokenId = MD5.GetMD5Code(userName + userPwd);
		users = datastore.createQuery(Users.class)
						.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���δע��";
		}
		else{
			db.Disconnect();
			return "�û���¼�ɹ�";
		}
	}

	public Users getUsers() {
		return users;
	}
	
}
