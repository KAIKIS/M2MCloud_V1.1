package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.NodeAlarm;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
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
	private NodeAlarm alarm;
	
	public String insertNode(String tokenId, String productId, String mac, String nodeId,
			String alarmName, String alarmType, double threshold0, double threshold1){
		
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
					.field("mac").equal(mac)
					.field("node.nodeId").equal(nodeId);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "û���ҵ����ݵ�";//û���ҵ���Ʒ���豸�����ݵ㶼�������
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
					return "��������ɹ�";//û���ҵ���Ʒ���豸�����ݵ㶼�������
				}
				else{
					db.Disconnect();
					return "�Ѿ������˾���";//û���ҵ���Ʒ���豸�����ݵ㶼�������
				}
			}
		}
	}

	public NodeAlarm getAlarm() {
		return alarm;
	}
	
	
}
