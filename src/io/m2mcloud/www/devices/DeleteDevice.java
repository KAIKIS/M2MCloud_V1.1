package io.m2mcloud.www.devices;

import io.m2mcloud.www.collections.Devices;
import io.m2mcloud.www.collections.Products;
import io.m2mcloud.www.collections.Users;
import io.m2mcloud.www.database.DataBase;
import io.m2mcloud.www.md5.MD5;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.DB;


/**
 * 只有管理员才能删除设备
 * @author Kai
 *
 */
public class DeleteDevice {
	private DataBase db = new DataBase();
	private Datastore datastore;
	
	public String delete(String tokenId, String productId, String mac){
		
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
			return "用户无权删除设备";
		}
		else{//删除设备需要修改products中的total，删除设备下的数据
			Query<Devices> queryDevice = datastore.createQuery(Devices.class)
					.field("productId").equal(productId)
					.field("mac").equal(mac);
			Devices devices = queryDevice.get();
			if(devices == null){
				db.Disconnect();
				return "没有找到设备";
			}
			else{
				datastore.delete(queryDevice);
				
				//对products中的设备总数减1
				Query<Products> queryProduct = datastore.createQuery(Products.class)
						.field("productId").equal(productId);
				UpdateOperations<Products> updateProduct = datastore.createUpdateOperations(Products.class)
						.dec("total");
				UpdateResults results = datastore.update(queryProduct, updateProduct);
				
				//删除该设备下的数据
				
				return "删除设备成功";
			}
			
		}
		
	}

	
}
