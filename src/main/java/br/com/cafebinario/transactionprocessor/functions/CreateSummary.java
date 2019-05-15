package br.com.cafebinario.transactionprocessor.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cafebinario.transactionprocessor.domains.commums.SummaryItem;
import br.com.cafebinario.transactionprocessor.functions.dtos.Summary;

@Component
public class CreateSummary implements Function<List<? extends SummaryItem>, Summary>{

	@Autowired
	private SumQty sumQty;
	
	@Autowired
	private SumValue sumValue;
	
	@Override
	public Summary apply(final List<? extends SummaryItem> summaryItem) {
		
		return Summary //
				.builder() //
				.value(sum(summaryItem, sumValue, SummaryItem::getValue, () -> BigDecimal.ZERO)) //
				.qty(sum(summaryItem, sumQty, t -> BigInteger.ONE, () -> BigInteger.ZERO)) //
				.build();
	}

	private <T> T sum(final List<? extends SummaryItem> summaryItems, final BinaryOperator<T> sum, final Function<SummaryItem, T> getValue, final Supplier<T> orElse) {
		return summaryItems //
				.stream() //
				.map(getValue) //
				.reduce(sum) //
				.orElse(orElse.get());
	}

}
