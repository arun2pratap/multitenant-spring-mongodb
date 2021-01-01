package com.multitenant.entity;

import com.multitenant.SharedCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

@SharedCollection
public interface PlanetRepository extends MongoRepository<Planet,String> {
}
