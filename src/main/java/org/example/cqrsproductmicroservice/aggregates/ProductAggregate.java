package org.example.cqrsproductmicroservice.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.cqrsproductmicroservice.commands.CreateProductCommand;
import org.example.cqrsproductmicroservice.commands.UpdateProductCommand;
import org.example.cqrsproductmicroservice.events.ProductCreatedEvent;
import org.example.cqrsproductmicroservice.events.ProductUpdatedEvent;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if (command.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (command.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getPrice(),
                command.getStock()
        ));
    }

    @CommandHandler
    public void handle(UpdateProductCommand command) {
        if (command.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (command.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        AggregateLifecycle.apply(new ProductUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getPrice(),
                command.getStock()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.stock = event.getStock();
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.stock = event.getStock();
    }
}
