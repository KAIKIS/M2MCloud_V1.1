package io.m2mcloud.www.products;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetAllProducts {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<Products> products = new ArrayList<Products>();
	
	public String getAP(String tokenId){
		
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
			int size = users.getProducts().size();
			for (int i = 0; i < size; i++) {
				Products getProducts = datastore.createQuery(Products.class)
									.field("productId").equal(users.getProducts().get(i).getProductId()).get();
				products.add(getProducts);
			}
			return "��ȡ��Ʒ��Դ�ɹ�";
		}
		
	}

	public List<Products> getProducts() {
		return products;
	}

}
