package br.com.cafebinario.transactionprocessor.functions.internal;

import java.math.BigInteger;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Service;

@Service
public class SumQty implements BinaryOperator<BigInteger>{

	@Override
	public BigInteger apply(final BigInteger a, final BigInteger b) {
		
		return a.add(b);
	}
}
