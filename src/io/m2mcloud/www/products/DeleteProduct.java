package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 * ɾ��ָ���Ĳ�Ʒ��ֻ�й���Ա�ſ���ɾ��
 * @author Kai
 *
 */
public class DeleteProduct {
	
	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId){
		
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//��������û��Ƿ�ӵ�������Ʒ���Ƿ��ǹ���Ա 
		
		if(users == null){
			db.Disconnect();
			return "�û���Ȩɾ����Ʒ";
		}
		else{
			//ɾ��Users�µ�products��Ϣ
			UserProducts up1 = new UserProducts();
			up1.setProductId(productId);
			up1.setRole("admin");
			
			Query<Users> queryUser1 = datastore.createQuery(Users.class)
					.field("products")
					.hasThisElement(up1);
			UpdateOperations<Users> updateUser1 = datastore.createUpdateOperations(Users.class)
					.removeAll("products", up1);

			datastore.update(queryUser1, updateUser1);
			
			UserProducts up2 = new UserProducts();
			up2.setProductId(productId);
			up2.setRole("share");
			
			Query<Users> queryUser2 = datastore.createQuery(Users.class)
					.field("products")
					.hasThisElement(up2);
			UpdateOperations<Users> updateUser2 = datastore.createUpdateOperations(Users.class)
					.removeAll("products", up2);

			datastore.update(queryUser2, updateUser2);
	
			//ɾ��Devices�µ�productId��Ϣ
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
												.field("productId").equal(productId);
			datastore.delete(queryDevice);
			
			//ɾ��Products��Ϣ
			Query<Products> queryProduct = datastore.createQuery(Products.class)
												.field("productId").equal(productId);
			datastore.delete(queryProduct);
			
			//ɾ���豸��Ϣ�µ�������Ϣ
			
			
			db.Disconnect();
			return "ɾ����Ʒ�ɹ�";
			
		}
		
		
	}
	
}
