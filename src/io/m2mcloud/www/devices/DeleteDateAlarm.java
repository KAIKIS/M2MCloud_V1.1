package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * ֻ�й���Ա���ܲ���
 * @author Kai
 *
 */
public class DeleteDateAlarm {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String deleteAlarm(String tokenId, String productId, String mac, String nodeId){
		
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
			return "�û���Ȩɾ��������Ϣ";
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
				for (int i = 0; i < devices.getAlarm().size(); i++) {
					if(devices.getAlarm().get(i).getNodeId().equals(nodeId)){
						devices.getAlarm().remove(i--);
						datastore.save(devices);
						db.Disconnect();
						return "ɾ��������Ϣ�ɹ�"; 
					}
				}
				db.Disconnect();
				return "û���ҵ�������Ϣ"; 
				
			}
		}
		
	}
	
	
}
