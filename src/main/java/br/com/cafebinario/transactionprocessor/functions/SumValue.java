package br.com.cafebinario.transactionprocessor.functions;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Component;

@Component
public class SumValue implements BinaryOperator<BigDecimal>{

	@Override
	public BigDecimal apply(final BigDecimal a, final BigDecimal b) {
		
		return a.add(b);
	}
}