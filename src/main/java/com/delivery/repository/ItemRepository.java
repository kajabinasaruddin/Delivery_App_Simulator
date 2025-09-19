package com.delivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.delivery.entity.Item;

public interface ItemRepository extends MongoRepository<Item,String>{

}
