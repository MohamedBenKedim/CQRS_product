package org.example.cqrsproductmicroservice.repositories;

import org.example.cqrsproductmicroservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}