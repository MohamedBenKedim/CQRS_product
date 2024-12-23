package org.example.cqrsproductmicroservice.events;

import lombok.Data;

@Data
public class ProductUpdatedEvent {
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public ProductUpdatedEvent(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }


   }
