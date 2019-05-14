package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Summary {

	private final BigDecimal value;
	private final BigInteger qty;
}
