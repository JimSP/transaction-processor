package br.com.cafebinario.transactionprocessor.domains.transactions.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionStep {

	private final Long identifier;
	private final Transaction transaction;
	private final StepType stepType;
}