package br.com.cafebinario.transactionprocessor.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.Summary;

@Component
public class CreateSummary implements Function<List<Transaction>, Summary>{

	@Autowired
	private SumQty sumQty;
	
	@Autowired
	private SumValue sumValue;
	
	@Override
	public Summary apply(final List<Transaction> transactions) {
		
		return Summary //
				.builder() //
				.value(sum(transactions, sumValue, Transaction::getValue, () -> BigDecimal.ZERO)) //
				.qty(sum(transactions, sumQty, t -> BigInteger.ONE, () -> BigInteger.ZERO)) //
				.build();
	}

	private <T> T sum(final List<Transaction> transactions, final BinaryOperator<T> sum, final Function<Transaction, T> getValue, final Supplier<T> orElse) {
		return transactions //
				.stream() //
				.map(getValue) //
				.reduce(sum) //
				.orElse(orElse.get());
	}

}
