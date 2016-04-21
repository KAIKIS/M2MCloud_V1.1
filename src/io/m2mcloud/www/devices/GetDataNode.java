package io.m2mcloud.www.devices;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.DataNode;
import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * �����û�����ʹ��
 * @author Kai
 *
 */
public class GetDataNode {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<DataNode> node = new ArrayList<DataNode>(); 
	
	public String getNode(String tokenId, String productId, String mac){
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���Ȩע���豸";
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
				
				
				
				
			}
		}
	}

	public List<DataNode> getNode() {
		return node;
	}

}
