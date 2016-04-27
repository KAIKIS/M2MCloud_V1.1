package io.m2mcloud.www.products;

import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.collections.UserProducts;
import io.m2mcloud.www.database.DataBase;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
/**
 * ֻ�й���Ա����ɾ��
 * @author Kai
 *
 */
public class DeleteShare {

	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId, String shareName){
		
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
			return "�û���Ȩɾ��";
		}
		else{//�жϷ����ߴ治����
			Users share = datastore.createQuery(Users.class)
					.field("userName").equal(shareName)
					.field("products.productId").equal(productId).get();
			if(share == null){
				db.Disconnect();
				return "�����߲�����";
			}
			else{//�жϸ÷�������û�б�����ò�Ʒ
				int size = share.getProducts().size();
				for (int i = 0; i < size; i++) {
					if(!share.getProducts().get(i).getProductId().equals(productId)){
						db.Disconnect();
						return "�÷����߻�û�б�����";
					}
				}
				UserProducts up = new UserProducts();
				up.setProductId(productId);
				up.setRole("share");
				Query<Users> queryProducts = datastore.createQuery(Users.class)
						.field("products")
						.hasThisElement(up);
				UpdateOperations<Users> updateProducts = datastore.createUpdateOperations(Users.class)
															.removeAll("products", up);
				UpdateResults results = datastore.update(queryProducts, updateProducts);
				db.Disconnect();
				return "ɾ�������߳ɹ�";
			}
		}
		
	}
}
