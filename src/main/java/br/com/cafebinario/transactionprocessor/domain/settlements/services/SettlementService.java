package br.com.cafebinario.transactionprocessor.domain.settlements.services;

import java.util.List;

import br.com.cafebinario.transactionprocessor.domain.settlements.models.Settlement;
import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;

public interface SettlementService {

	public List<Settlement> schedule(final List<Transaction> transactions);
	
}
