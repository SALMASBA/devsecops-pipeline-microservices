package com.example.customer_service;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
    @Bean
	CommandLineRunner commandLineRunner(CustomerRepository repository, CustomerRepository customerRepository) {
	return args -> {
		customerRepository.save(Customer.builder()
						.name("salma").email("rahili@gmail.com")
				.build());
		customerRepository.save(Customer.builder()
				.name("Souad").email("souad@gmail.com")
				.build());
		customerRepository.save(Customer.builder()
				.name("Youssef").email("youssef@gmail.com")
				.build());
		customerRepository.findAll().forEach(c->{
			System.out.println("============");
			System.out.println(c.getId());
			System.out.println(c.getName());
			System.out.println(c.getEmail());
			System.out.println("============");
		});
	};
	}
}
