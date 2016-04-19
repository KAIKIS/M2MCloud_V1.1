package io.m2mcloud.www.products;

import java.util.Date;

import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;

public class CreateProduct {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Products products;
	public String insertProduct(String tokenId, String productName, String description){
		
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
		else{//��Ҫ��products�����в������users������ͬʱ���в���
			Date date = new Date();
			products = new Products();
			String productId = MD5.GetMD5Code(tokenId + date.toString());//ʹ��tokenId��ʱ����
			String secret = MD5.GetMD5Code(date.toString());

			products.setProductId(productId);
			products.setSecret(secret);
			products.setProductName(productName);
			products.setDescription(description);
			products.setCreateTime(date);
			products.setOnline(0);
			products.setTotal(0);
			datastore.save(products);
			
			UserProducts userProducts = new UserProducts();
			userProducts.setProductId(productId);
			userProducts.setRole("admin");
			userProducts.setProductCreateTime(date);
			users.getProducts().add(userProducts);
			datastore.save(users);
			
			db.Disconnect();
			return "��Ʒ�����ɹ�";
		}
	}
	
	public Products getProducts() {
		return products;
	}
	
	
}
