package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
/**
 * 只有管理员才能操作，并且删除数据点会同时删除警告信息
 * @author Kai
 *
 */
public class DeleteDataNode {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String deleteNode(String tokenId, String productId, String mac, String nodeId){
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
			return "用户无权删除数据点";
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
			else{//要删除两部分信息
				
				for (int i = 0; i < devices.getNode().size(); i++) {
					if(devices.getNode().get(i).getNodeId().equals(nodeId)){
						devices.getNode().remove(i--);
						for (int j = 0; j < devices.getAlarm().size(); j++) {
							if(devices.getAlarm().get(i).getNodeId().equals(nodeId)){
								devices.getAlarm().remove(j--);
							}
						}
						datastore.save(devices);
						db.Disconnect();
						return "删除警告信息成功"; 
					}
				}
				db.Disconnect();
				return "没有找到数据节点信息";
			}
		}
	}
}
