package com.delivery.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @JsonProperty("userId")
    private String customerId;

    private String address;
    private Double price;
    private String status;  // NEW, PROCESSING, DELIVERED, CANCELLED
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    private List<String> itemIds;

    public Order() {}

    public Order(String id, String customerId, String address, Double price, String status, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<String> itemIds) {
		//super();
		this.id = id;
		this.customerId = customerId;
		this.address = address;
		this.price = price;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.itemIds = itemIds;
	}

	public List<String> getItemIds() { 
    	return itemIds; 
    	}
    public void setItemIds(List<String> itemIds) { 
    		this.itemIds = itemIds;
    	}

    public String getId() { 
    		return id; 
    	}
    public void setId(String id) { 
    		this.id = id; 
    	}

    public String getCustomerId() { 
    		return customerId; 
    	}
    public void setCustomerId(String customerId) {
    		this.customerId = customerId; 
    	}
    public String getAddress() { 
    		return address; 
    	}
    public void setAddress(String address) { 
    		this.address = address; 
    	}

    public Double getPrice() {
    		return price; 
    	}
    public void setPrice(Double price) {
    		this.price = price; 
    	}

    public String getStatus() { 
    		return status; 
    	}
    public void setStatus(String status) {
    		this.status = status;
    	}
    public LocalDateTime getCreatedAt() { 
    		return createdAt; 
    	}
    public void setCreatedAt(LocalDateTime createdAt) {
    		this.createdAt = createdAt; 
    	}

    public LocalDateTime getUpdatedAt() { 
    		return updatedAt; 
    	}
    public void setUpdatedAt(LocalDateTime updatedAt) {
    		this.updatedAt = updatedAt; 
    	}
}
