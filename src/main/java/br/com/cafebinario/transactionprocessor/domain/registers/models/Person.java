package br.com.cafebinario.transactionprocessor.domain.registers.models;

import java.util.List;

import lombok.Data;

@Data
public abstract class Person {
	
	private final PersonType personType;
	private final List<Address> addresses;
	private final List<Phone> phones;
	private final List<Document> documents;
	
}
