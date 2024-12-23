package org.example.cqrsproductmicroservice.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateProductCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public UpdateProductCommand(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
