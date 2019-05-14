package br.com.cafebinario.transactionprocessor.functions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cafebinario.transactionprocessor.domain.settlements.models.Settlement;
import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.SettlementReport;
import br.com.cafebinario.transactionprocessor.functions.dtos.TransactionClassifier;

@Component
public class CreateSettlementReport
		implements BiFunction<Entry<TransactionClassifier, List<Transaction>>, LocalDate, SettlementReport> {

	@Autowired
	private CreateSummary createSummary;

	@Override
	public SettlementReport apply(final Entry<TransactionClassifier, List<Transaction>> entry,
			final LocalDate settlementDate) {
		
		return SettlementReport //
				.builder() //
				.transactionClassifier(entry.getKey()) //
				.settlement(Settlement //
						.builder() //
						.settlementDate(settlementDate) //
						.summary(createSummary.apply(entry.getValue())) //
						.transactions(entry.getValue()) //
						.build()) //
				.build();
	}
}
