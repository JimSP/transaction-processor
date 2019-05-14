package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.time.LocalDate;

import br.com.cafebinario.transactionprocessor.domain.cards.models.Product;
import br.com.cafebinario.transactionprocessor.domain.transactions.models.Reason;
import br.com.cafebinario.transactionprocessor.domain.transactions.models.WayPayment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionClassifier {

	private final LocalDate transactionDate;
	
	private final Product product;
	
	private final WayPayment wayPayment;
	
	private final Long storeIdentifier;
	
	private final Long terminalIdentifier;
	
	private final Reason reason;
	
	public TransactionClassifier(
			final LocalDate transactionDate,
			final Product product,
			final WayPayment wayPayment,
			final Long storeIdentifier,
			final Long terminalIdentifier,
			final Reason reason) {
		
		this.transactionDate = transactionDate;
		this.product = product;
		this.wayPayment = wayPayment;
		this.storeIdentifier = storeIdentifier;
		this.terminalIdentifier = terminalIdentifier;
		this.reason = reason;
	}
}
