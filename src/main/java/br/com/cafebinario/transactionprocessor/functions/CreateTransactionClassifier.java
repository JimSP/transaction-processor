package br.com.cafebinario.transactionprocessor.functions;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.filters.TransactionClassifier;

@Service
public class CreateTransactionClassifier implements Function<Transaction, TransactionClassifier> {

	@Override
	public TransactionClassifier apply(final Transaction transaction) {

		return TransactionClassifier //
				.builder() //
				.product(transaction.getCard().getProduct()) //
				.reason(transaction.getReason()) //
				.storeIdentifier(transaction.getStore().getIdentifier()) //
				.terminalIdentifier(transaction.getTerminal().getIdentifier()) //
				.transactionDate(transaction.getDateTime().toLocalDate()) //
				.wayPayment(transaction.getWayPayment()) //
				.build();
	}
}