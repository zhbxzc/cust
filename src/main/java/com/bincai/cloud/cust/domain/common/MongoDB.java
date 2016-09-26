package com.bincai.cloud.cust.domain.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDB {
	private final MongoTemplate mongoTemplate;
	@Autowired
    public MongoDB(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
}
