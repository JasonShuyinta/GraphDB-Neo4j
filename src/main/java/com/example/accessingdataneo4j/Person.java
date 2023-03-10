package com.example.accessingdataneo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Relationship(type = "TEAMMATE")
    public Set<Person> teammates;

    @Relationship(type = "OWNS")
    public Set<Dog> dogs;

    public void worksWith(Person person) {
        if(teammates == null) {
            teammates = new HashSet<>();
        }
        teammates.add(person);
    }

    public void owns(Dog dog) {
        if(dogs == null) {
            dogs = new HashSet<>();
        }
        dogs.add(dog);
    }

    public String toString() {
        return this.name + "'s teammates => " + Optional.ofNullable(this.teammates).orElse(
                Collections.emptySet()).stream().map(Person::getName).toList();
    }
}
