package br.com.cafebinario.transactionprocessor.domains.stores.models;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.registers.models.Address;
import br.com.cafebinario.transactionprocessor.domains.registers.models.Document;
import br.com.cafebinario.transactionprocessor.domains.registers.models.Phone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
public class Company extends Store {

	private static final long serialVersionUID = -142844955863081809L;

	private final Store headOffice;
	private final List<Store> stores;
	
	@JsonCreator
	public Company(
			@JsonProperty final Long identifier,
			@JsonProperty final String commercialName,
			@JsonProperty final String socialName,
			@JsonProperty final List<Address> addresses,
			@JsonProperty final List<Phone> phones,
			@JsonProperty final List<Document> documents,
			@JsonProperty final Store headOffice,
			@JsonProperty final List<Store> stores) {
		
		super(identifier, commercialName, socialName, addresses, phones, documents, Collections.emptyList());
		
		this.headOffice = headOffice;
		this.stores = stores;
	}
}
