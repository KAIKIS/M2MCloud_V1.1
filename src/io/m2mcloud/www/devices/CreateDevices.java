package io.m2mcloud.www.devices;

import java.util.Date;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;


/**
 * ֻ�й���Ա���ܴ���
 * @author Kai
 *
 */
public class CreateDevices {
	
	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String insertDevice(String tokenId, String productId, String mac, String deviceName,
			String address, String longitude, String latitude){
		
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
			return "�û���Ȩע���豸";
		}
		else{//����Ҫ�޸�total
			Date date = new Date();
			String deviceId = MD5.GetMD5Code(productId + mac);
			Devices devices = new Devices();
			devices.setDeviceId(deviceId);
			devices.setMac(mac);
			devices.setProductId(productId);
			devices.setDeviceName(deviceName);
			devices.setAddress(address);
			devices.setLongitude(longitude);
			devices.setLatitude(latitude);
			devices.setCreateTime(date);
			devices.setOnline(false);
			
			datastore.save(devices);
			
			Query<Products> queryProduct = datastore.createQuery(Products.class)
										.field("productId").equal(productId);
			UpdateOperations<Products> updateProduct = datastore.createUpdateOperations(Products.class)
										.inc("total", 1);
			UpdateResults results = datastore.update(queryProduct, updateProduct);
			db.Disconnect();
			return "�豸�����ɹ�";
		}
	}

}
