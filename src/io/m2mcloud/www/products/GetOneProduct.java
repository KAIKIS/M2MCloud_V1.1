package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetOneProduct {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Products products;
	
	public String getOP(String tokenId, String productId){
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
			products = datastore.createQuery(Products.class)
							.field("productId").equal(productId).get();
			if(products == null){
				db.Disconnect();
				return "�ò�Ʒ������";
			}
			else{
				db.Disconnect();
				return "��ȡ��Ʒ�ɹ�";
			}
			
		}
	}

	public Products getProducts() {
		return products;
	}
	
}
