package org.example.cqrsproductmicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

    @Data
    @Entity
    public class Product {
        @Id
        private String id;
        private String name;
        private String description;
        private double price;
        private int stock;

        // Constructors
        public Product() {}

        public Product(String id, String name, String description, double price, int stock) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.stock = stock;
        }

    }
