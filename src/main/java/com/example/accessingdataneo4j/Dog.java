package com.example.accessingdataneo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String race;

    public Dog(String race) {
        this.race = race;
    }

    @Relationship(type = "PLAYS_WITH")
    public Set<Toy> toys;

    public void playsWith(Toy toy) {
        if (toys == null) {
            toys = new HashSet<>();
        }
        toys.add(toy);
    }
}
