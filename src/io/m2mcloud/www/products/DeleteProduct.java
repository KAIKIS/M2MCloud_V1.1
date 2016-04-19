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
			return "�û��޷�ɾ����Ʒ";
		}
		else{
			//ɾ��Users�µ�products��Ϣ
			UserProducts up = new UserProducts();
			up.setProductId(productId);
			up.setRole("admin");
			Query<Users> queryProducts = datastore.createQuery(Users.class)
					.field("products")
					.hasThisElement(up);
			UpdateOperations<Users> updateProducts = datastore.createUpdateOperations(Users.class)
					.removeAll("products", up);

			UpdateResults results = datastore.update(queryProducts, updateProducts);
			
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
