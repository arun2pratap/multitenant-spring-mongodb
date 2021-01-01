package com.multitenant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoConfigProperties {
    @Value("${spring.data.mongodb.database:global}")
    private String dataBaseName;

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
