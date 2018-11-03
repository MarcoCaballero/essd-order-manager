package io.github.marcocaballero;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(OrderRepository orderRepository){
		return args -> {
			OrderEntity order1 = new OrderEntity("House Order");
			order1.setItems(Arrays.asList(
									new ItemEntity("Fruit"),
									new ItemEntity("Milk")));
									
			orderRepository.save(order1);
			
			OrderEntity order2 = new OrderEntity("Job Order");
			order2.setItems(Arrays.asList(
									new ItemEntity("Laptop"),
									new ItemEntity("Monitor"),
									new ItemEntity("Keyboard")));
			orderRepository.save(order2);
		};
	}
}
