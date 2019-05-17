package br.com.cafebinario.transactionprocessor.domains.registers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Address implements Serializable{

	private static final long serialVersionUID = -1024766474715497601L;

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
