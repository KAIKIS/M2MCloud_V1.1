package io.m2mcloud.www.dao;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class Database {

	private Morphia morphia;
	private Datastore datastore;
	private MongoCredential credential;
	private ServerAddress sAddress;
	private MongoClient mongoClient;
	
	private String adminName = "Admin";
	private String adminPwd = "123456";
	private String dbName = "m2m";
	private String address = "localhost";
	private int port = 27017;
	
	public Database(){
	}
	
	public Database(String adminName, String adminPwd, String dbName, String address, int port){
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.dbName = dbName;
		this.address = address;
		this.port = port;
	}
	
	public void ConnectDB(){
		credential = MongoCredential.createCredential(adminName, dbName, adminPwd.toCharArray());
		sAddress = new ServerAddress(address,port);
		mongoClient = new MongoClient(sAddress,Arrays.asList(credential));
		morphia = new Morphia();
		morphia.mapPackage("io.m2mcloud.www.collections");
		datastore = morphia.createDatastore(mongoClient, dbName);
		datastore.ensureIndexes();
	}
	
	public void Disconnect(){
		mongoClient.close();
	}
	
	public Datastore getDatastore() {
		return datastore;
	}
	
}
