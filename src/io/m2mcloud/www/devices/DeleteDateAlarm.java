package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 只有管理员才能操作
 * @author Kai
 *
 */
public class DeleteDateAlarm {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String deleteAlarm(String tokenId, String productId, String mac, String nodeId){
		
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户无权删除警告信息";
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
				for (int i = 0; i < devices.getAlarm().size(); i++) {
					if(devices.getAlarm().get(i).getNodeId().equals(nodeId)){
						devices.getAlarm().remove(i--);
						datastore.save(devices);
						db.Disconnect();
						return "删除警告信息成功"; 
					}
				}
				db.Disconnect();
				return "没有找到警告信息"; 
				
			}
		}
		
	}
	
	
}
