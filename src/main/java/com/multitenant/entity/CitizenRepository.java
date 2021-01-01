package com.multitenant.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CitizenRepository extends MongoRepository<Citizen,String> {
}
