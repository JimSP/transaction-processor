package br.com.cafebinario.transactionprocessor.functions;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Component;

@Component
public class SumQty implements BinaryOperator<BigInteger>{

	@Override
	public BigInteger apply(final BigInteger a, final BigInteger b) {
		
		return a.add(b);
	}
}
