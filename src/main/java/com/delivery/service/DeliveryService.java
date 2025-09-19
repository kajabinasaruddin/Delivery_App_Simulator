package com.delivery.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.delivery.entity.Delivery;
import com.delivery.repository.DeliveryRepository;


@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepo;

    public DeliveryService(DeliveryRepository deliveryRepo) {
        this.deliveryRepo = deliveryRepo;
    }

    public Delivery getStatus(String orderId) {
        return deliveryRepo.findByOrderId(orderId);
    }

    public Delivery updateStatus(String orderId, String status) {
        Delivery delivery = deliveryRepo.findByOrderId(orderId);
        if (delivery == null) {
            delivery = new Delivery();
            delivery.setOrderId(orderId);
        }
        delivery.setStatus(status);
        delivery.setUpdatedAt(LocalDateTime.now());
        return deliveryRepo.save(delivery);
    }
}