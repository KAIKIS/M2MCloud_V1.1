package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.DataNode;
import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.NodeAlarm;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

public class GetNodeAlarm {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<NodeAlarm> alarms = new ArrayList<NodeAlarm>(); 
	
	public String get(String tokenId, String productId, String mac){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户无权注册设备";
		}
		else{
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
					.field("productId").equal(productId)
					.field("mac").equal(mac);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "没有找到设备";
			}
			else{
				int size = devices.getAlarm().size();
				for (int i = 0; i < size; i++) {
					alarms.add(devices.getAlarm().get(i));
				}
				db.Disconnect();
				return "获取设备警告成功";
			}
		}
	}

	public List<NodeAlarm> getAlarms() {
		return alarms;
	}
	
}
