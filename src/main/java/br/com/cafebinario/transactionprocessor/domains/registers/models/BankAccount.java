package br.com.cafebinario.transactionprocessor.domains.registers.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccount {

	private final String accountNumber;
	private final String agency;
	private final String bankNumber;
	private final Document document;
	private final Person person;
	
	@JsonCreator
	public BankAccount(
			@JsonProperty final String accountNumber,
			@JsonProperty final String agency,
			@JsonProperty final String bankNumber,
			@JsonProperty final Document document,
			@JsonProperty final Person person) {

		this.accountNumber = accountNumber;
		this.agency = agency;
		this.bankNumber = bankNumber;
		this.document = document;
		this.person = person;
	}	
}
