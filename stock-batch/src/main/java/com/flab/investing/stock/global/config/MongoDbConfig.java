package com.flab.investing.stock.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.flab.investing.stock")
@EnableMongoAuditing
public class MongoDbConfig {
}
