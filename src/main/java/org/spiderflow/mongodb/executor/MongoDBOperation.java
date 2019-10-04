package org.spiderflow.mongodb.executor;

import java.util.HashMap;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoDBOperation extends HashMap<String, Object>{
	
	private static final long serialVersionUID = 2327254189435235565L;
	
	private DB db;
	
	public MongoDBOperation() {
		super();
	}

	public MongoDBOperation(DB db) {
		super();
		this.db = db;
	}
	
	@Override
	public Object get(Object key) {
		if(key == null){
			throw new NullPointerException("collection不能为空");
		}
		DBCollection collection = db.getCollection(key.toString());
		return collection;
	}


}
