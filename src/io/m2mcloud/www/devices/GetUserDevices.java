package io.m2mcloud.www.devices;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;

/**
 * 不管是管理员还是分享者都可以获取数据
 * @author Kai
 *
 */
public class GetUserDevices {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<Devices> getDevices = new ArrayList<Devices>();
	
	public String getUD(String tokenId){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户还未注册";
		}
		else{//获取Users下的products有多少对应的productId，使用productId在Devices中找到所有的设备
			int size = users.getProducts().size();
			for (int i = 0; i < size; i++) {
				List<Devices> devices = datastore.createQuery(Devices.class)
						.field("productId").equal(users.getProducts().get(i).getProductId()).asList();
					getDevices.addAll(devices);	
			}
			db.Disconnect();
			return "获取用户设备成功";
		}
	}

	public List<Devices> getGetDevices() {
		return getDevices;
	}
	
	
}
