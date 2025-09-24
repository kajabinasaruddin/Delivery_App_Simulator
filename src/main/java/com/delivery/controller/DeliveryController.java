package com.delivery.controller;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<Object> getStatus(@PathVariable String orderId) {
        Delivery deliver = deliveryService.getStatus(orderId);

        if (deliver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", "Delivery not found for Order ID " + orderId
            ));
        }

        return ResponseEntity.ok(Map.of(
            "message", "Delivery status fetched successfully",
            "data", deliver
        ));
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Object> cancelOrder(@PathVariable String orderId) {
        Delivery deliver = deliveryService.getStatus(orderId);

        if (deliver == null) {
            return ResponseEntity.notFound().build();
        }

        String status = deliver.getStatus() != null ? deliver.getStatus().toUpperCase() : "";

        if ("DELIVERED".equals(status)) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Order already delivered, cannot cancel", "data", deliver));
        }

        if ("CANCELLED".equals(status)) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Order already cancelled", "data", deliver));
        }

        Delivery updated = deliveryService.updateStatus(orderId, "CANCELLED");

        return ResponseEntity.ok(Map.of("message", "Order cancelled successfully", "data", updated));
    }

    @PutMapping("/{orderId}/update")
    public ResponseEntity<Object> updateStatus(@PathVariable String orderId,
                                               @RequestBody Delivery request) {
        Delivery updated = deliveryService.updateStatus(orderId, request.getStatus());
        return ResponseEntity.ok(Map.of(
            "message", "Delivery status updated successfully",
            "data", updated
        ));
    }
}