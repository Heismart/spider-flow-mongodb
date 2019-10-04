package org.spiderflow.mongodb.service;

import org.spiderflow.mongodb.mapper.MongoDataSourceMapper;
import org.spiderflow.mongodb.model.MongoDataSource;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class MongoDataSourceService extends ServiceImpl<MongoDataSourceMapper, MongoDataSource>{

}
