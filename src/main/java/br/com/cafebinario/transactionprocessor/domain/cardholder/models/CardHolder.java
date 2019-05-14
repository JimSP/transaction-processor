package br.com.cafebinario.transactionprocessor.domain.cardholder.models;

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
public class CardHolder extends Person{

	private final String firstName;
	private final String secoundName;
	private final String lastName;
	
	@Builder
	@JsonCreator(mode=Mode.PROPERTIES)
	CardHolder(
			
			@JsonProperty final String firstName,
			@JsonProperty final String secoundName,
			@JsonProperty final String lastName,
			@JsonProperty final List<Address> addresses,
			@JsonProperty final List<Phone> phones,
			@JsonProperty final List<Document> documents) {
		
		super(PersonType.PRIVATE, addresses, phones, documents);
		
		this.firstName = firstName;
		this.secoundName = secoundName;
		this.lastName = lastName;
	}	
}
