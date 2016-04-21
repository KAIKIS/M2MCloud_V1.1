package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.DataNode;
import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import java.util.Date;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 只有admin才能操作
 * @author Kai
 *
 */
public class CreateDataAlarm {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String insertNode(String tokenId, String productId, String mac, String nodeId,
			String alarmName, String alarmType, Object threshold0, Object threshold1){
		
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
			return "用户无权添加警告";
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
				Date date = new Date();
				int size =  devices.getNode().size();
				for (int i = 0; i < size; i++) {
					if(devices.getNode().get(i).getNodeId().equals(nodeId)){
						DataNode node = devices.getNode().get(i);
						
						node.setAlarmName(alarmName);
						node.setAlarmType(alarmType);
						node.setThreshold0(threshold0);
						node.setThreshold1(threshold1);
						node.setAlarmCreateTime(date);
						datastore.save(node);
						db.Disconnect();
						return "插入警告成功";
					}
				}
				db.Disconnect();
				return "没有找到数据节点";
			}
		}
	}
}
