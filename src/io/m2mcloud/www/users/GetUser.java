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
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���δע��";
		}
		else{
			db.Disconnect();
			return "��ȡ�û���Ϣ�ɹ�";
		}
	}
	
	public Users getUsers() {
		return users;
	}
	
}
