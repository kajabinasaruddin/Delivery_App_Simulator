package com.delivery.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delivery.entity.Item;
import com.delivery.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping
    public List<Item> getItems() {
        return itemService.getAllItems();
    }
    
    @PostMapping("/add")
    public String addItem(@RequestBody Item item) {
        Item saved = itemService.addItem(item);
        return "Item added successfully: " + saved.getId();
    }
    @PutMapping("/{id}")
    public String updateItem(@PathVariable String id, @RequestBody Item item) {
        Item updated = itemService.updateItem(id, item);

        if (updated != null) {
            return "Item updated successfully"; 
        } else {
            return "Item not found with ID: " + id;
        }
    }


}
