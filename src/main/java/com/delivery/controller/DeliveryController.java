package com.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.entity.Delivery;
import com.delivery.service.DeliveryService;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{orderId}/status")
    public Delivery getStatus(@PathVariable String orderId) {
        return deliveryService.getStatus(orderId);
    }

    @PutMapping("/{orderId}/update")
    public Delivery updateStatus(@PathVariable String orderId, @RequestParam String status) {
        return deliveryService.updateStatus(orderId, status);
    }
}