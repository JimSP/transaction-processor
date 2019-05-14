package br.com.cafebinario.transactionprocessor.facade;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domain.transactions.services.TransactionService;

@Service
public class DefaultTransactionFacade implements TransactionService {

	@Override
	public List<Transaction> getTransactions(final LocalDate from, final LocalDate to, final Transaction example) {
		
		return Collections.emptyList();
	}

	@Override
	public List<Transaction> getTransactions(final LocalDate from, final LocalDate to) {
		
		return Collections.emptyList();
	}

	@Override
	public Transaction create(final Transaction transaction) {
		
		return Transaction.builder().build();
	}

	@Override
	public Transaction cancel(final Transaction transaction) {
		
		return Transaction.builder().build();
	}

	@Override
	public Transaction reversal(final Transaction transaction) {
		
		return Transaction.builder().build();
	}

	@Override
	public Transaction chargeback(final Transaction transaction) {
		
		return Transaction.builder().build();
	}

	@Override
	public Transaction systemReversal(final Transaction transaction) {
		
		return Transaction.builder().build();
	}
	
	@Override
	public Transaction fraud(final Transaction transaction) {
		
		return Transaction.builder().build();
	}
}
