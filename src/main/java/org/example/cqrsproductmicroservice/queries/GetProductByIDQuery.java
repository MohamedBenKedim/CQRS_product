package org.example.cqrsproductmicroservice.queries;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, toString, etc.
@AllArgsConstructor // Generates constructor with all arguments
@NoArgsConstructor
public class GetProductByIDQuery {
    private String id;
}
