package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettlementReport implements Serializable {

	private static final long serialVersionUID = -9025672279165139259L;

	private final TransactionClassifier transactionClassifier;
	private final Settlement settlement;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public SettlementReport(
			@JsonProperty final TransactionClassifier transactionClassifier,
			@JsonProperty final Settlement settlement) {

		this.transactionClassifier = transactionClassifier;
		this.settlement = settlement;
	}
}
