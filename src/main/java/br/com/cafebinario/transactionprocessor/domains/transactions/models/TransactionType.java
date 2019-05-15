package br.com.cafebinario.transactionprocessor.domains.transactions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
	CREDIT(Boolean.FALSE),
	DEBIT(Boolean.FALSE),
	CANCELATION(Boolean.TRUE),
	CHARGEBACK(Boolean.TRUE),
	REVERSAL(Boolean.TRUE),
	SYSTEM_REVERSAL(Boolean.TRUE);
	
	private final Boolean hasOriginalTransaction;
}
