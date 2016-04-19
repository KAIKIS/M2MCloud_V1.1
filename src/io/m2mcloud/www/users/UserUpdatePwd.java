package io.m2mcloud.www.users;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

public class UserUpdatePwd {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private Users users;
	
	public String UpdatePwd(String tokenId, String userName, String userOldPwd, String userNewPwd){
		
		if(!db.Connect()){
			return "�������ݿ�ʧ��";
		}
		String oldTokenId = MD5.GetMD5Code(userName + userOldPwd);
		if(!oldTokenId.equals(tokenId)){
			return "�û�������û������������";
		}
		else{
			datastore = db.getDatastore();
			users = datastore.createQuery(Users.class)
					.field("tokenId").equal(tokenId).get();//��ȡ���ҵ��ĵ�һ���ĵ���
			if(users == null){
				db.Disconnect();
				return "�û���δע��";
			}
			else{
				String newTokenId = MD5.GetMD5Code(userName + userNewPwd);
				Query<Users> queryUser = datastore.createQuery(Users.class)
												.field("tokenId").equal(tokenId);
				UpdateOperations<Users> updateUser = datastore.createUpdateOperations(Users.class)
																	.set("tokenId", newTokenId);
				UpdateResults results = datastore.update(queryUser, updateUser);
				db.Disconnect();
				return "�û��޸�����ɹ�";
			}
		}
	}
	
}
