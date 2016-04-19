package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;

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
				.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���δע��";
		}
		else{
			String deviceId = MD5.GetMD5Code(productId + mac);
			Devices devices = new Devices();
			devices.setDeviceId(deviceId);
			devices.setMac(mac);
			devices.setProductId(productId);
			devices.setDeviceName(deviceName);
			devices.setAddress(address);
			devices.setLongitude(longitude);
			devices.setLatitude(latitude);
			
			datastore.save(devices);
			db.Disconnect();
			return "�豸�����ɹ�";
		}
		
		
	}

}
