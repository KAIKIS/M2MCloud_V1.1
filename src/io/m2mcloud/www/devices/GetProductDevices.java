package io.m2mcloud.www.devices;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

public class GetProductDevices {
	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<Devices> getDevices = new ArrayList<Devices>();
	
	public String getPD(String tokenId, String productId){
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
			List<Devices> devices = datastore.createQuery(Devices.class)
					.field("productId").equal(productId).asList();
			getDevices.addAll(devices);	
			db.Disconnect();
			return "��ȡ��Ʒ�豸�ɹ�";
		}
	}

	public List<Devices> getGetDevices() {
		return getDevices;
	}
}
