package br.com.cafebinario.transactionprocessor.domains.settlements.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.Summary;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Settlement implements Serializable{

	private static final long serialVersionUID = 6708645111410960794L;

	private final Long identifier;
	private final List<Transaction> transactions;
	private final LocalDate settlementDate;
	private final Summary summary;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Settlement(
			@JsonProperty final Long identifier,
			@JsonProperty final List<Transaction> transactions,
			@JsonProperty final LocalDate settlementDate,
			@JsonProperty final Summary summary) {
		
		this.identifier = identifier;
		this.transactions = transactions;
		this.settlementDate = settlementDate;
		this.summary = summary;
	}
}
