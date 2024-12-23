package org.example.cqrsproductmicroservice.controllers;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.cqrsproductmicroservice.commands.CreateProductCommand;
import org.example.cqrsproductmicroservice.commands.UpdateProductCommand;
import org.example.cqrsproductmicroservice.entities.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/products/command")
public class ProductCommandController {
    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @PostMapping
    public CompletableFuture<String> createProduct(@RequestBody Product product) {
        String id = UUID.randomUUID().toString();
        CreateProductCommand command = new CreateProductCommand(
                id,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
        return commandGateway.send(command);
    }

    @PutMapping("/{id}")
    public CompletableFuture<String> updateProduct(@PathVariable String id, @RequestBody Product product) {
        UpdateProductCommand command = new UpdateProductCommand(
                id,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
        return commandGateway.send(command);
    }
}
