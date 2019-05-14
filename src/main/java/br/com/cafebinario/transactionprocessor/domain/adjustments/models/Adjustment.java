package br.com.cafebinario.transactionprocessor.domain.adjustments.models;

import br.com.cafebinario.transactionprocessor.domain.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Adjustment {

	private Long identifier;
	private Settlement settlement;
	private AdjustmentType adjustmentType;
}
