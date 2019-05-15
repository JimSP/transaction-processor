package br.com.cafebinario.transactionprocessor.domains.adjustments.models;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Adjustment {

	private Long identifier;
	private Settlement settlement;
	private AdjustmentType adjustmentType;
}
