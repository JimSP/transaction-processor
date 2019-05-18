package br.com.cafebinario.transactionprocessor.functions.dtos.filters;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.TransactionStep;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MonitorType {

	TRANSACTION_STEP(TransactionStep.class);

	private final Class<? extends Monitorable> type;

}
