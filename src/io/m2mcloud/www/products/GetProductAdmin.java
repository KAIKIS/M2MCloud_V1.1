package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetProductAdmin {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private String adminName;
	
	public String getPA(String tokenId, String productId){
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���δע��";
		}
		else{
			Users admin = datastore.createQuery(Users.class)
								.field("products.productId").equal(productId)
								.field("products.role").equal("admin").get();
			adminName = admin.getUserName();
			db.Disconnect();
			return "��ȡ��Ʒ����Ա�ɹ�";
		}
	}

	public String getAdminName() {
		return adminName;
	}
	
}
