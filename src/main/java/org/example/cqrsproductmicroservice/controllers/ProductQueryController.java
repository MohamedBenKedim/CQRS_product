package org.example.cqrsproductmicroservice.controllers;


import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.cqrsproductmicroservice.entities.Product;
import org.example.cqrsproductmicroservice.queries.GetAllProductsQuery;
import org.example.cqrsproductmicroservice.queries.GetProductByIDQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/products/command")
public class ProductQueryController {
    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{id}")
    public CompletableFuture<Product> getProduct(@PathVariable String id) {
        return queryGateway.query(
                new GetProductByIDQuery(id),
                ResponseTypes.instanceOf(Product.class)
        );
    }

    @GetMapping
    public CompletableFuture<List<Product>> getAllProducts() {
        return queryGateway.query(
                new GetAllProductsQuery(),
                ResponseTypes.multipleInstancesOf(Product.class)
        );
    }

}
