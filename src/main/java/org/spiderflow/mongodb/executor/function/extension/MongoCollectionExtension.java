package org.spiderflow.mongodb.executor.function.extension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.spiderflow.executor.FunctionExtension;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class MongoCollectionExtension implements FunctionExtension{
	
	@Override
	public Class<?> support() {
		return MongoCollection.class;
	}
	
	public static void insert(MongoCollection<Document> collection,List<Map<String,Object>> maps){
		collection.insertMany(maps.stream().map(e->new Document(e)).collect(Collectors.toList()));
	}
	
	public static void insert(MongoCollection<Document> collection,Map<String,Object> map){
		insert(collection,Arrays.asList(map));
	}
	
	public static FindIterable<Document> find(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.find(new Document(query));
	}
	
	public static long update(MongoCollection<Document> collection,Map<String,Object> query,Map<String,Object> update){
		return collection.updateOne(new Document(query), new Document(update)).getModifiedCount();
	}
	
	public static long updateMany(MongoCollection<Document> collection,Map<String,Object> query,Map<String,Object> update){
		return collection.updateMany(new Document(query), new Document(update)).getModifiedCount();
	}
	

	public static long count(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.count(new Document(query));
	}
	
	public static long remove(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.deleteMany(new Document(query)).getDeletedCount();
	}
	
	public static long removeOne(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.deleteOne(new Document(query)).getDeletedCount();
	}
}
