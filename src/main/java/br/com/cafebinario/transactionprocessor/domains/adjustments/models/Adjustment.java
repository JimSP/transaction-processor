package br.com.cafebinario.transactionprocessor.domains.adjustments.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Adjustment implements Serializable {

	private static final long serialVersionUID = -1323872995562913000L;

	private Long identifier;
	private Settlement settlement;
	private AdjustmentType adjustmentType;
	
	@JsonCreator
	private Adjustment(
			@JsonProperty final Long identifier,
			@JsonProperty final Settlement settlement,
			@JsonProperty final AdjustmentType adjustmentType) {
		
		this.identifier = identifier;
		this.settlement = settlement;
		this.adjustmentType = adjustmentType;
	}
}
