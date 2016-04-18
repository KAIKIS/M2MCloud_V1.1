package io.m2mcloud.www.dao;

public class test1 {

	public void ceshi1(){
		Database database = new Database();
		database.ConnectDB();
		System.out.println(database.getDatastore());
	}
	
}
