package com.bapple.resource;

import java.util.Iterator;

import com.bapple.ConnectionManagerFactory;
import com.bapple.QueryCriteria;
import com.bapple.Server;
import com.bapple.TableName;
import com.mongodb.BasicDBList;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ResourceBase {
	protected static String USER_PAULA = "Paula";
	protected static String USER_ID = null;

	/**
	 * TODO: this method goes away once OAuth is working.  It is here merely to
	 * provide automatic "authentication" until the OAuth solution is in place.
	 */
	protected String getUserUuid() {
		if (USER_ID == null) {
			DB db = ConnectionManagerFactory.getFactory().getConnection();
			DBCollection coll = db.getCollection(TableName.USERS);
			DBObject obj = coll.findOne(QueryCriteria.getByName(USER_PAULA));
			USER_ID = obj.get("_id").toString();
		}
		
		return USER_ID;
	}

	/**
	 * This method replaces the 'collections' id values for an item with href oriented
	 * URL values.
	 */
	protected void replaceCollectionUuidsWObjRefs(DBObject o) {
		BasicDBList collectionsField = (BasicDBList)o.get("collections");
		BasicDBList replacementCollectionsField = new BasicDBList();
		
		Iterator<Object> i = collectionsField.iterator();
		while (i.hasNext()) {
			String strCollectionUuid = i.next().toString();
			String strHrefValue = Server.getBaseUrl() +"/collections/" + strCollectionUuid;
			replacementCollectionsField.add(strHrefValue);
		}
		o.removeField("collections");
		o.put("collections", replacementCollectionsField);
	}

}
