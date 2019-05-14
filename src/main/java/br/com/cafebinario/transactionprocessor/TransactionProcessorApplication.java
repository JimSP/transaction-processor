package br.com.cafebinario.transactionprocessor;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class TransactionProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionProcessorApplication.class, args);
	}
	
	@Bean
	@Primary
	public Validator springValidator() {
	    return new LocalValidatorFactoryBean();
	}
}
