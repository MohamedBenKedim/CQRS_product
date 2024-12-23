package org.example.cqrsproductmicroservice.exceptions;

public class ProductNotFoundExeption extends RuntimeException  {

    public ProductNotFoundExeption(String message) {
        super(message);
    }
}
