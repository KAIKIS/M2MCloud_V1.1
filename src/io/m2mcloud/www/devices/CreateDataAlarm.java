package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.NodeAlarm;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
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
	private NodeAlarm alarm;
	
	public String insertNode(String tokenId, String productId, String mac, String nodeId,
			String alarmName, String alarmType, double threshold0, double threshold1){
		
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
					.field("mac").equal(mac)
					.field("node.nodeId").equal(nodeId);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "没有找到数据点";//没有找到产品、设备、数据点都会有这个
			}
			else{
				Query<Devices> queryNode = datastore.createQuery(Devices.class)
						.field("alarm.nodeId").equal(nodeId);
				if(queryNode.get() == null){
					Date date = new Date();
					alarm = new NodeAlarm();
					
					alarm.setNodeId(nodeId);
					alarm.setAlarmName(alarmName);
					alarm.setAlarmType(alarmType);
					alarm.setThreshold0(threshold0);
					alarm.setThreshold1(threshold1);
					alarm.setAlarmCreateTime(date);
					
					devices.getAlarm().add(alarm);
					datastore.save(devices);
					db.Disconnect();
					return "创建警告成功";//没有找到产品、设备、数据点都会有这个
				}
				else{
					db.Disconnect();
					return "已经插入了警告";//没有找到产品、设备、数据点都会有这个
				}
			}
		}
	}

	public NodeAlarm getAlarm() {
		return alarm;
	}
	
	
}
