package com.multitenant.config;

import com.mongodb.client.MongoClient;
import com.multitenant.SharedCollection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = SharedCollection.class),
        mongoTemplateRef = "mongoTemplateShared"
)
public class SharedMongoConfig {

    @Bean
    public MongoTemplate mongoTemplateShared(MongoClient mongoClient, MongoConfigProperties mongoConfigProperties, MappingMongoConverter converter) {
        MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient, mongoConfigProperties.getDataBaseName());
        MongoTemplate template = new MongoTemplate(mongoDbFactory, converter);
        return template;
    }
}

