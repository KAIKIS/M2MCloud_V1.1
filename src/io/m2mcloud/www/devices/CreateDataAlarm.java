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
 * ֻ��admin���ܲ���
 * @author Kai
 *
 */
public class CreateDataAlarm {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String insertNode(String tokenId, String productId, String mac, String nodeId,
			String alarmName, String alarmType, Object threshold0, Object threshold1){
		
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
			return "�û���Ȩ��Ӿ���";
		}
		else{
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
					.field("productId").equal(productId)
					.field("mac").equal(mac);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "û���ҵ��豸";
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
						return "���뾯��ɹ�";
					}
				}
				db.Disconnect();
				return "û���ҵ����ݽڵ�";
			}
		}
	}
}
