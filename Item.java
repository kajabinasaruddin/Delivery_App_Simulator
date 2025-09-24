package com.delivery.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "items")
public class Item {
    @Id
    private String id;

    private String name;
    private Double price;
    private Integer quantity = 1;
    
    public Item() {
		
	}
	public Item(String id, String name, Double price, Integer quantity) {
		//super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
