package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.DB;


/**
 * ֻ�й���Ա����ɾ���豸
 * @author Kai
 *
 */
public class DeleteDevice {
	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId, String mac){
		
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���Ȩɾ���豸";
		}
		else{//ɾ���豸��Ҫ�޸�products�е�total��ɾ���豸�µ�����
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
					.field("productId").equal(productId)
					.field("mac").equal(mac);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "û���ҵ��豸";
			}
			else{
				datastore.delete(queryDevice);
				
				//��products�е��豸������1
				Query<Products> queryProduct = datastore.createQuery(Products.class)
						.field("productId").equal(productId);
				UpdateOperations<Products> updateProduct = datastore.createUpdateOperations(Products.class)
						.dec("total");
				UpdateResults results = datastore.update(queryProduct, updateProduct);
				
				//ɾ�����豸�µ�����
				
				return "ɾ���豸�ɹ�";
			}
			
		}
		
	}

	
}
