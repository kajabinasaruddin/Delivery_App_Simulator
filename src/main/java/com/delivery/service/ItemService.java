package com.delivery.service;

import java.util.List;

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
		if(itemRepo.findById(id) != null) {
			System.out.println("Item found");
		}else {
			System.out.println("Item not found::");
		}
		return itemRepo.save(updated);
}

}