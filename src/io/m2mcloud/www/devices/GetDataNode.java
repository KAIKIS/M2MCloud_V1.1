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
 * 所有用户都能使用
 * @author Kai
 *
 */
public class GetDataNode {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<DataNode> node = new ArrayList<DataNode>(); 
	
	public String getNode(String tokenId, String productId, String mac){
		if(!db.Connect()){
			return "连接数据库失败";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId).get();//获取查找到的第一个文档。
		if(users == null){
			db.Disconnect();
			return "用户无权注册设备";
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
				
				
				
				
			}
		}
	}

	public List<DataNode> getNode() {
		return node;
	}

}
