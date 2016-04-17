package io.m2mcloud.www.collections;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

@Entity(value = "users", noClassnameStored = true)
public class Users {

	@Id
	private ObjectId id;
	private String tokenId;
	
	@Indexed(options = @IndexOptions(unique = true))
	private String userName;
	private Date createTime;
	
}
