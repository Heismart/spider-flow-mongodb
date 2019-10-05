package org.spiderflow.mongodb.executor.function.extension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.spiderflow.annotation.Comment;
import org.spiderflow.annotation.Example;
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
	
	@Comment("mongodb插入数据")
	@Example("${mongodb.aliasName.collectionName.insert([{key : value},{key : value}])}")
	public static void insert(MongoCollection<Document> collection,List<Map<String,Object>> maps){
		collection.insertMany(maps.stream().map(e->new Document(e)).collect(Collectors.toList()));
	}
	
	@Comment("mongodb插入数据")
	@Example("${mongodb.aliasName.collectionName.insert({key : value})}")
	public static void insert(MongoCollection<Document> collection,Map<String,Object> map){
		insert(collection,Arrays.asList(map));
	}
	
	@Comment("mongodb查找数据")
	@Example("${mongodb.aliasName.collectionName.find({key : value})}")
	public static FindIterable<Document> find(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.find(new Document(query));
	}
	
	@Comment("mongodb修改数据")
	@Example("${mongodb.aliasName.collectionName.update({key : oldValue},{key : newValue})}")
	public static long update(MongoCollection<Document> collection,Map<String,Object> query,Map<String,Object> update){
		return collection.updateOne(new Document(query), new Document(update)).getModifiedCount();
	}
	
	@Comment("mongodb修改数据")
	@Example("${mongodb.aliasName.collectionName.updateMany({key : oldValue},{key : newValue})}")
	public static long updateMany(MongoCollection<Document> collection,Map<String,Object> query,Map<String,Object> update){
		return collection.updateMany(new Document(query), new Document(update)).getModifiedCount();
	}
	
	@Comment("mongodb查找总数")
	@Example("${mongodb.aliasName.collectionName.count({key : value})}")
	public static long count(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.count(new Document(query));
	}
	
	@Comment("mongodb删除数据")
	@Example("${mongodb.aliasName.collectionName.remove({key : value})}")
	public static long remove(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.deleteMany(new Document(query)).getDeletedCount();
	}
	
	@Comment("mongodb删除数据")
	@Example("${mongodb.aliasName.collectionName.removeOne({key : value})}")
	public static long removeOne(MongoCollection<Document> collection,Map<String,Object> query){
		return collection.deleteOne(new Document(query)).getDeletedCount();
	}
}
