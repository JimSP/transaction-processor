package br.com.cafebinario.transactionprocessor.domains.transactions.models;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class TransactionStep implements Monitorable {

	private static final long serialVersionUID = 8987591822787333764L;

	private final Transaction transaction;
	private final StepType stepType;

	@JsonCreator(mode = Mode.PROPERTIES)
	public TransactionStep(@JsonProperty final Transaction transaction, @JsonProperty final StepType stepType) {

		this.transaction = Optional //
				.ofNullable(transaction) //
				.orElse(Transaction //
						.builder() //
						.build());

		this.stepType = stepType;
	}

	@Override
	public Long getIdentifier() {

		return transaction.getIdentifier();
	}
}
