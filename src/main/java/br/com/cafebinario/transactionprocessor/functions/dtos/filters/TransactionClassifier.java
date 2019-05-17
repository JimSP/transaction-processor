package br.com.cafebinario.transactionprocessor.functions.dtos.filters;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.cards.models.Product;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Reason;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.WayPayment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionClassifier  implements Serializable {

	private static final long serialVersionUID = -7888977785988842452L;

	private final LocalDate transactionDate;
	
	private final Product product;
	
	private final WayPayment wayPayment;
	
	private final Long storeIdentifier;
	
	private final Long terminalIdentifier;
	
	private final Reason reason;
	
	@JsonCreator
	public TransactionClassifier(
			@JsonProperty final LocalDate transactionDate,
			@JsonProperty final Product product,
			@JsonProperty final WayPayment wayPayment,
			@JsonProperty final Long storeIdentifier,
			@JsonProperty final Long terminalIdentifier,
			@JsonProperty final Reason reason) {
		
		this.transactionDate = transactionDate;
		this.product = product;
		this.wayPayment = wayPayment;
		this.storeIdentifier = storeIdentifier;
		this.terminalIdentifier = terminalIdentifier;
		this.reason = reason;
	}
}
