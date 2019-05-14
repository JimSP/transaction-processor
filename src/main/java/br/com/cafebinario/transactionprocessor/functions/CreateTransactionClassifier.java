package br.com.cafebinario.transactionprocessor.functions;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.TransactionClassifier;

@Component
public class CreateTransactionClassifier implements Function<Transaction, TransactionClassifier> {

	@Override
	public TransactionClassifier apply(final Transaction transaction) {

		return TransactionClassifier //
				.builder() //
				.product(transaction.getProduct()) //
				.reason(transaction.getReason()) //
				.storeIdentifier(transaction.getStore().getIdentifier()) //
				.terminalIdentifier(transaction.getTerminal().getIdentifier()) //
				.transactionDate(transaction.getDateTime().toLocalDate()) //
				.wayPayment(transaction.getWayPayment()) //
				.build();
	}
}