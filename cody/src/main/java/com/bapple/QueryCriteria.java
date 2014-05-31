package com.bapple;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * This class consolidates the creation of BasicDBObject instances that are
 * used as "query criteria" for Mongo find statements.  This exists with the 
 * hopes of making it easier to rename the fields in the documents stored
 * in the Mongo database.  
 *
 */
public class QueryCriteria {
	public static BasicDBObject getById(String strId) {
		return new BasicDBObject("_id", strId);
	}

	public static BasicDBObject getByName(String strName) {
		return new BasicDBObject("name", strName);
	}
	
	public static BasicDBObject getByUser(String strUser) {
		return new BasicDBObject("user", strUser);
	}

	public static BasicDBObject getByUserCollection(String strUserUuid, String strCollection) {
		return new BasicDBObject("user", strUserUuid).append("collections", strCollection);
	}

}
