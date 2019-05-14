package br.com.cafebinario.transactionprocessor.domain.registers.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phone {
	
	private final String ddi;
	private final String ddd;
	private final String number;
	private final PhoneType phoneType;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Phone(
			@JsonProperty final String ddi,
			@JsonProperty final String ddd,
			@JsonProperty final String number,
			@JsonProperty final PhoneType phoneType) {
		
		this.ddi = ddi;
		this.ddd = ddd;
		this.number = number;
		this.phoneType = phoneType;
	}
}
