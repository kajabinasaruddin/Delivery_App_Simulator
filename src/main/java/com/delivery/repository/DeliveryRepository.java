package com.delivery.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.delivery.entity.Delivery;
@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Delivery findByOrderId(String orderId);
}
