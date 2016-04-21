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
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户还未注册";
		}
		else{
			List<Devices> devices = datastore.createQuery(Devices.class)
					.field("productId").equal(productId).asList();
			getDevices.addAll(devices);	
			db.Disconnect();
			return "获取产品设备成功";
		}
	}

	public List<Devices> getGetDevices() {
		return getDevices;
	}
}
