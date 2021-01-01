package com.multitenant.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.multitenant.TenantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


public class MultiTenantMongoDBFactory extends SimpleMongoClientDatabaseFactory {

    @Autowired
    private TenantHolder tenantHolder;
    private final String globalDB;

    public MultiTenantMongoDBFactory(MongoClient mongoClient, String globalDB) {
        super(mongoClient, globalDB);
        this.globalDB = globalDB;
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(getTenantDatabase());
    }

    protected String getTenantDatabase() {
        String tenantId = tenantHolder.getTenantId();
        if (tenantId != null) {
            return tenantId;
        } else {
            return globalDB;
        }
    }
}
