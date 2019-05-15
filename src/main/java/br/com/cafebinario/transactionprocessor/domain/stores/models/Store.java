package br.com.cafebinario.transactionprocessor.domain.stores.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.cafebinario.transactionprocessor.domain.registers.models.Address;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Document;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Person;
import br.com.cafebinario.transactionprocessor.domain.registers.models.PersonType;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Phone;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class Store extends Person{

	private static final long serialVersionUID = -5163299780727679819L;

	private final Long identifier;
	private final String commercialName;
	private final String socialName;
	
	@Builder
	@JsonCreator(mode=Mode.PROPERTIES)
	public Store(
			@JsonProperty final Long identifier,
			@JsonProperty final String commercialName,
			@JsonProperty final String socialName,
			@JsonProperty final List<Address> addresses,
			@JsonProperty final List<Phone> phones,
			@JsonProperty final List<Document> documents) {
		
		super(PersonType.COMMERCIAL, addresses, phones, documents);
		
		this.identifier = identifier;
		this.commercialName = commercialName;
		this.socialName = socialName;
	}	
}
