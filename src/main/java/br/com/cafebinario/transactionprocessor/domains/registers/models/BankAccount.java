package br.com.cafebinario.transactionprocessor.domains.registers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class BankAccount implements Serializable{

	private static final long serialVersionUID = -4575920905620481981L;

	private final String accountNumber;
	private final String agency;
	private final String bankNumber;
	private final Document document;
	private final Person person;
	
	@JsonCreator(mode=Mode.PROPERTIES)
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
