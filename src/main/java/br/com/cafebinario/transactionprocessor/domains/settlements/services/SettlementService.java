package br.com.cafebinario.transactionprocessor.domains.settlements.services;

import java.util.List;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;

public interface SettlementService {

	public List<Settlement> schedule(final List<Transaction> transactions);
	
}
