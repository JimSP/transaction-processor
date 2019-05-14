package br.com.cafebinario.transactionprocessor.domain.cardholder.models;

import java.util.List;

import br.com.cafebinario.transactionprocessor.domain.registers.models.Address;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Document;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Person;
import br.com.cafebinario.transactionprocessor.domain.registers.models.PersonType;
import br.com.cafebinario.transactionprocessor.domain.registers.models.Phone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Data
public class CardHolder extends Person{

	private final String firstName;
	private final String secoundName;
	private final String lastName;
	
	CardHolder(
			
			final String firstName,
			final String secoundName,
			final String lastName,
			final List<Address> addresses,
			final List<Phone> phones,
			final List<Document> documents) {
		
		super(PersonType.PRIVATE, addresses, phones, documents);
		
		this.firstName = firstName;
		this.secoundName = secoundName;
		this.lastName = lastName;
	}	
}
