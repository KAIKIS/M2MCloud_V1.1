package io.m2mcloud.www.products;

import java.util.ArrayList;
import java.util.List;

import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * �����û�����ʹ��
 * @author Kai
 *
 */
public class GetProductUsers {

	private DataBase db = new DataBase();
	private Datastore datastore;
	private List<String> userNames = new ArrayList<String>();
	private List<UserProducts> userProducts = new ArrayList<UserProducts>();
	
	public String getPU(String tokenId, String productId){
		
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
		else{
			List<Users> queryUsers = datastore.createQuery(Users.class)
					.field("products.productId").equal(productId).asList();
			int size = queryUsers.size();
			for (int i = 0; i < size; i++) {
				userNames.add(queryUsers.get(i).getUserName());
				userProducts.addAll((queryUsers.get(i).getProducts()));
			}
			for (int i = 0; i < userProducts.size(); i++) {
				if(!userProducts.get(i).getProductId().equals(productId)){
					userProducts.remove(i--);
				}
			}
			db.Disconnect();
			return "��ȡ��Ʒ�µ��û��ɹ�";
		}
		
	}

	public List<String> getUserNames() {
		return userNames;
	}

	public List<UserProducts> getUserProducts() {
		return userProducts;
	}
	
}
