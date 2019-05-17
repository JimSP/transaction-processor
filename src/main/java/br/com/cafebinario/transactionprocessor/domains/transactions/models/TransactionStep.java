package br.com.cafebinario.transactionprocessor.domains.transactions.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TransactionStep {

	private final Transaction transaction;
	private final StepType stepType;

	@JsonCreator(mode = Mode.PROPERTIES)
	public TransactionStep(
			@JsonProperty final Transaction transaction,
			@JsonProperty final StepType stepType) {
		
		this.transaction = transaction;
		this.stepType = stepType;
	}
}
