package br.com.cafebinario.transactionprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@SpringBootApplication
public class TransactionProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionProcessorApplication.class, args);
	}
}
