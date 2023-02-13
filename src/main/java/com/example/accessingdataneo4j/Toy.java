package com.example.accessingdataneo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Toy {

    @Id
    @GeneratedValue
    private Long id;

    private double price;

    private String name;

    public Toy(double price, String name) {
        this.price = price;
        this.name = name;
    }
}
