package com.multitenant.config;

import com.multitenant.SharedCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = SharedCollection.class))
public class MultiTenantMongoAppConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private MongoConfigProperties mongoConfigProperties;

    @Override
    protected String getDatabaseName() {
        return null;
    }

    @Override
    @Primary
    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        String globalDB = mongoConfigProperties.getDataBaseName();
        return new MultiTenantMongoDBFactory(mongoClient(), globalDB);
    }

    @Bean
    public MongoTemplate mongoTemplateShared(MongoConfigProperties mongoConfigProperties) {
        MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient(), mongoConfigProperties.getDataBaseName());
        return new MongoTemplate(mongoDbFactory);
    }
}
