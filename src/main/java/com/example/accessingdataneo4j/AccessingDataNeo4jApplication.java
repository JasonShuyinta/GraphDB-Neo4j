package com.example.accessingdataneo4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
@Slf4j
public class AccessingDataNeo4jApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AccessingDataNeo4jApplication.class, args);
		System.exit(0);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {
			personRepository.deleteAll();

			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person craig = new Person("Craig");
			Dog labrador = new Dog("Labrador");
			Dog pitbull = new Dog("Pitbull");
			Dog chihuaha = new Dog("Chihuaha");
			Dog amstaff = new Dog("Amstaff");
			Toy ball = new Toy(12.3, "Ball");
			Toy chain = new Toy(2.3, "Chain");
			Toy doll = new Toy(1.4, "Doll");


			List<Person> team = Arrays.asList(greg, roy, craig);
			log.info("Before linking up to Neo4j..");
			team.stream().forEach(person -> log.info("\t"+ person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.owns(labrador);
			greg.owns(pitbull);
			labrador.playsWith(ball);
			labrador.playsWith(doll);
			labrador.playsWith(chain);
			pitbull.playsWith(chain);
			greg.worksWith(craig);
			craig.owns(chihuaha);
			chihuaha.playsWith(chain);
			craig.owns(labrador);
			roy.owns(chihuaha);
			chihuaha.playsWith(ball);
			chihuaha.playsWith(doll);
			roy.owns(amstaff);
			amstaff.playsWith(chain);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);
			//We already know that roy works with greg
			personRepository.save(roy);

			//We know now craig works with roy and greg

			log.info("Lookup each person by name");
			team.stream().forEach(person -> log.info("\t" +
					personRepository.findByName(person.getName()).toString()));

			List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
			log.info("The following have Greg as a teammate");
			teammates.stream().forEach(person -> log.info("\t" + person.getName()));
		};
	}

}
