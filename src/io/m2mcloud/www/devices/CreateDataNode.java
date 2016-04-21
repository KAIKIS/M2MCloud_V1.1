package io.m2mcloud.www.devices;

import java.util.Date;

import io.m2mcloud.www.collections.DataNode;
import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 只有管理员才能操作
 * @author Kai
 *
 */
public class CreateDataNode {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private DataNode node;
	
	public String insertNode(String tokenId, String productId, String mac, String nodeName,
			String nodeType, String units, String nodeDescription){
		
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
			return "用户无权添加节点";
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
				//一个设备可以有多种数据点，但是查看时可以获取nodeId来选择显示哪个数据点
				//若把nodeName设为唯一索引，则在创建设备时就需要输入节点信息
				//另一种解决方法是将节点信息作为单独的一个集合保存，但对于1对很少来说有些浪费
				node = new DataNode();
				Date date = new Date();
				
				node.setNodeId(MD5.GetMD5Code(date.toString()));//使用时间做种子
				node.setNodeName(nodeName);
				node.setNodeType(nodeType);
				node.setUnits(units);
				node.setNodeDescription(nodeDescription);
				node.setStore(false);//默认不存储
				node.setNodeCreateTime(date);
				
				devices.getNode().add(node);
				datastore.save(devices);
				db.Disconnect();
				return "插入数据点成功";
			}
		}
	}

	public DataNode getNode() {
		return node;
	}
	
}
