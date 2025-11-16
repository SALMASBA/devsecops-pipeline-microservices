package com.example.biling_service;

import com.example.biling_service.entities.Bill;
import com.example.biling_service.entities.ProductItem;
import com.example.biling_service.feign.CustomerRestClient;
import com.example.biling_service.feign.ProductRestClient;
import com.example.biling_service.model.Customer;
import com.example.biling_service.model.Product;
import com.example.biling_service.repository.BillRepository;
import com.example.biling_service.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository,
										ProductItemRepository productItemRepository,
										CustomerRestClient customerRestClient,
										ProductRestClient productRestClient) {
		return args -> {
			// Récupération des clients et produits via Feign
			Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();

			Random random = new Random();

			for (Customer customer : customers) {
				if (customer.getId() == null) {
					System.out.println("Skipping customer without ID: " + customer.getName());
					continue; // éviter NullPointerException
				}

				// Création de la facture pour le client
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customer.getId())
						.build();
				billRepository.save(bill);

				// Création des items de produits pour cette facture
				for (Product product : products) {
					if (product.getId() == null || product.getPrice() == null) continue;

					ProductItem productItem = ProductItem.builder()
							.bill(bill)
							.productId(product.getId())
							.quantity(1 + random.nextInt(10))
							.unitPrice(product.getPrice())
							.build();
					productItemRepository.save(productItem);
				}
			}

			System.out.println("Data initialization completed successfully!");
		};
	}
}
