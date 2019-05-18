package br.com.cafebinario.transactionprocessor.domains.transactions.services;

import java.time.LocalDate;
import java.util.List;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;

public interface TransactionService {

	List<Transaction> getTransactions(final LocalDate from, final LocalDate to, final Transaction example);
	List<Transaction> getTransactions(final LocalDate from, final LocalDate to);
	Transaction getTransaction(final Long identifier);
	Transaction create(final Transaction transaction);
	Transaction cancel(final Transaction transaction);
	Transaction reversal(final Transaction transaction);
	Transaction chargeback(final Transaction transaction);
	Transaction systemReversal(final Transaction transaction);
	Transaction fraud(final Transaction transaction);
	void monitoring(final Transaction transaction);
}
