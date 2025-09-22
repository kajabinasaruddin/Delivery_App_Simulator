package com.delivery.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addItem(@RequestBody Item item) {
        Item saved = itemService.addItem(item);
        return ResponseEntity.ok(Map.of("message", "Item added successfully", "data", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable String id, @RequestBody Item item) {
        Item updated = itemService.updateItem(id, item);
        return ResponseEntity.ok(Map.of("message", "Item updated successfully", "data", updated));
    }
}