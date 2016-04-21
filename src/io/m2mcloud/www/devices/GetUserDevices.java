package io.m2mcloud.www.devices;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

/**
 * �����ǹ���Ա���Ƿ����߶����Ի�ȡ����
 * @author Kai
 *
 */
public class GetUserDevices {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<Devices> getDevices = new ArrayList<Devices>();
	
	public String getUD(String tokenId){
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���δע��";
		}
		else{//��ȡUsers�µ�products�ж��ٶ�Ӧ��productId��ʹ��productId��Devices���ҵ����е��豸
			int size = users.getProducts().size();
			for (int i = 0; i < size; i++) {
				List<Devices> devices = datastore.createQuery(Devices.class)
						.field("productId").equal(users.getProducts().get(i).getProductId()).asList();
					getDevices.addAll(devices);	
			}
			db.Disconnect();
			return "��ȡ�û��豸�ɹ�";
		}
	}

	public List<Devices> getGetDevices() {
		return getDevices;
	}
	
	
}
