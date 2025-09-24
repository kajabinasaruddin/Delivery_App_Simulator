package com.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.delivery.entity.Item;
import com.delivery.repository.ItemRepository;


@Service
public class ItemService {
    private final ItemRepository itemRepo;

    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    public Item updateItem(String id, Item updated) {
    		Optional<Item> optionalItem = itemRepo.findById(id);

        if (optionalItem.isPresent()) {
            Item existing = optionalItem.get();
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setQuantity(updated.getQuantity());
            return itemRepo.save(existing); // only updates existing
        } else {
            System.out.println("Item not found");
            return null; 
        }
}

}
