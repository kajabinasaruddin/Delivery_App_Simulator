package com.delivery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entity.Order;
@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
	List<Order> findByCustomerId(String customerId);

}
