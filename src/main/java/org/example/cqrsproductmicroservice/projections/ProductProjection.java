package org.example.cqrsproductmicroservice.projections;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.example.cqrsproductmicroservice.entities.Product;
import org.example.cqrsproductmicroservice.events.ProductCreatedEvent;
import org.example.cqrsproductmicroservice.events.ProductUpdatedEvent;
import org.example.cqrsproductmicroservice.queries.GetAllProductsQuery;
import org.example.cqrsproductmicroservice.queries.GetProductByIDQuery;
import org.example.cqrsproductmicroservice.repositories.ProductRepository;

import java.util.List;

public class ProductProjection {
    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        Product product = new Product(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getPrice(),
                event.getStock()
        );
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) {
        Product product = new Product(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getPrice(),
                event.getStock()
        );
        productRepository.save(product);
    }

    @QueryHandler
    public Product handle(GetProductByIDQuery query) {
        return productRepository.findById(query.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @QueryHandler
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }


}
