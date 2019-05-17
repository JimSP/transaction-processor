package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Summary implements Serializable {

	private static final long serialVersionUID = -5337646536337637916L;

	private final BigDecimal value;
	private final BigInteger qty;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Summary(
			@JsonProperty final BigDecimal value,
			@JsonProperty final BigInteger qty) {

		this.value = value;
		this.qty = qty;
	}
}
