package com.delivery.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.delivery.entity.Item;
import com.delivery.entity.Order;
import com.delivery.repository.ItemRepository;
import com.delivery.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ItemRepository itemRepo;

    public OrderService(OrderRepository orderRepo, ItemRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
    }

    // ===================== Place a new order =====================
    public Order placeOrder(Order order) {
        double totalPrice = 0.0;

        if (order.getItemIds() != null && !order.getItemIds().isEmpty()) {
            for (int i = 0; i < order.getItemIds().size(); i++) {
                String itemId = order.getItemIds().get(i);
                Optional<Item> itemOpt = itemRepo.findById(itemId);
                if (itemOpt.isPresent()) {
                    Item item = itemOpt.get();
                    if (item.getPrice() != null) {
                        totalPrice += item.getPrice();
                    } else {
                        System.out.println("⚠ Item price is null for: " + itemId);
                    }
                } else {
                    System.out.println("⚠ Item not found: " + itemId);
                }
            }
        } else {
            System.out.println("⚠ No item IDs provided!");
        }

        order.setPrice(totalPrice);
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }

    // ===================== Update an existing order =====================
    public Order updateOrder(String id, Order updated) {
        // Fetch existing order
        Optional<Order> existingOpt = orderRepo.findById(id);
        if (!existingOpt.isPresent()) {
            throw new RuntimeException("Order not found: " + id);
        }
        Order existing = existingOpt.get();

        // Update address
        if (updated.getAddress() != null) {
            existing.setAddress(updated.getAddress());
        }

        // Update itemIds and recalculate price
        if (updated.getItemIds() != null && !updated.getItemIds().isEmpty()) {
            existing.setItemIds(updated.getItemIds());
            double totalPrice = 0.0;
            for (int i = 0; i < updated.getItemIds().size(); i++) {
                String itemId = updated.getItemIds().get(i);
                Optional<Item> itemOpt = itemRepo.findById(itemId);
                if (itemOpt.isPresent()) {
                    Item item = itemOpt.get();
                    if (item.getPrice() != null) {
                        totalPrice += item.getPrice();
                    } else {
                        System.out.println("⚠ Item price is null for: " + itemId);
                    }
                } else {
                    System.out.println("⚠ Item not found: " + itemId);
                }
            }
            existing.setPrice(totalPrice);
        }

        existing.setUpdatedAt(LocalDateTime.now());
        return orderRepo.save(existing);
    }

    // ===================== Cancel an order =====================
    public void cancelOrder(String id) {
        orderRepo.deleteById(id);
    }

    // ===================== Get all orders (or filter by ID) =====================
    public List<Order> getOrder(String id) {
        return orderRepo.findAll();
    }

    // ===================== Get order history by customer ID =====================
    public List<Order> getHistory(String customerId) {
        return orderRepo.findByCustomerId(customerId);
    }
}
