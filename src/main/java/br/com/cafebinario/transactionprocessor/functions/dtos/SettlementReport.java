package br.com.cafebinario.transactionprocessor.functions.dtos;

import br.com.cafebinario.transactionprocessor.domain.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettlementReport {

	private final TransactionClassifier transactionClassifier;
	private final Settlement settlement;
}
