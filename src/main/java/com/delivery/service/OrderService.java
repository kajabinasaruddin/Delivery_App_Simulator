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

    public Order placeOrder(Order order) {
        double totalPrice = 0.0;

        if (order.getItemIds() != null && !order.getItemIds().isEmpty()) {
            for (int i = 0; i < order.getItemIds().size(); i++) {
                String itemId = order.getItemIds().get(i);
                Optional<Item> itemOpt = itemRepo.findById(itemId);
                if (itemOpt.isPresent()) {
                    Item item = itemOpt.get();
                    if (item.getPrice() != null) {
                        totalPrice += item.getPrice()*item.getQuantity();
                    } else {
                        System.out.println("Item price is null for: " + itemId);
                    }
                } else {
                    System.out.println("Item not found: " + itemId);
                }
            }
        } else {
            System.out.println("No item IDs provided!");
        }

        order.setPrice(totalPrice);
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }


    public Order updateOrder(String id, Order updated) {
       
        Optional<Order> existingOpt = orderRepo.findById(id);
        if (!existingOpt.isPresent()) {
            throw new RuntimeException("Order not found: " + id);
        }
        Order existing = existingOpt.get();

       
        if (updated.getAddress() != null) {
            existing.setAddress(updated.getAddress());
        }
       
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

    public void cancelOrder(String id) {
        orderRepo.deleteById(id);
    }
    
    public Optional<Order> getOrder(String id) {
        return orderRepo.findById(id);
    }

    public List<Order> getHistory(String customerId) {
        return orderRepo.findByCustomerId(customerId);
    }
}
