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
 * ֻ�й���Ա���ܲ���
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
			return "�������ݿ�ʧ��";
		}
		datastore = db.getDatastore();
		Users users = datastore.createQuery(Users.class)
				.field("tokenId").equal(tokenId)
				.field("products.productId").equal(productId)
				.field("products.role").equal("admin").get();//��ȡ���ҵ��ĵ�һ���ĵ���
		if(users == null){
			db.Disconnect();
			return "�û���Ȩ��ӽڵ�";
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
				//һ���豸�����ж������ݵ㣬���ǲ鿴ʱ���Ի�ȡnodeId��ѡ����ʾ�ĸ����ݵ�
				//����nodeName��ΪΨһ���������ڴ����豸ʱ����Ҫ����ڵ���Ϣ
				//��һ�ֽ�������ǽ��ڵ���Ϣ��Ϊ������һ�����ϱ��棬������1�Ժ�����˵��Щ�˷�
				node = new DataNode();
				Date date = new Date();
				
				node.setNodeId(MD5.GetMD5Code(date.toString()));//ʹ��ʱ��������
				node.setNodeName(nodeName);
				node.setNodeType(nodeType);
				node.setUnits(units);
				node.setNodeDescription(nodeDescription);
				node.setStore(false);//Ĭ�ϲ��洢
				node.setNodeCreateTime(date);
				
				devices.getNode().add(node);
				datastore.save(devices);
				db.Disconnect();
				return "�������ݵ�ɹ�";
			}
		}
	}

	public DataNode getNode() {
		return node;
	}
	
}
