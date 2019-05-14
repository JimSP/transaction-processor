package br.com.cafebinario.transactionprocessor.domain.registers.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

	private final String street;
	private final Integer number;
	private final String complement;
	private final String neighborhood;
	private final String city;
	private final String province;
	private final String country;
	private final String cep;
	private final String reference;
	private final AddressType addressType;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Address(
			@JsonProperty final String street,
			@JsonProperty final Integer number,
			@JsonProperty final String complement,
			@JsonProperty final String neighborhood,
			@JsonProperty final String city,
			@JsonProperty final String province,
			@JsonProperty final String country,
			@JsonProperty final String cep,
			@JsonProperty final String reference,
			@JsonProperty final AddressType addressType) {
		
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.province = province;
		this.country = country;
		this.cep = cep;
		this.reference = reference;
		this.addressType = addressType;
	}
	
	
}
