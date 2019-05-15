package br.com.cafebinario.transactionprocessor.domain.registers.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 7432016461088604396L;

	private final PersonType personType;
	private final List<Address> addresses;
	private final List<Phone> phones;
	private final List<Document> documents;

}
