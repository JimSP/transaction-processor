package br.com.cafebinario.transactionprocessor.domains.agreements.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.cafebinario.transactionprocessor.domains.cards.models.Product;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.TransactionType;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.WayPayment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Agreement implements Serializable {

	private static final long serialVersionUID = -3614884897407007433L;

	private String mcc;
	private String cnae;
	private Product product;
	private WayPayment wayPayment;
	private TransactionType transactionType;
	private BigDecimal tax;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Agreement(
			final String mcc,
			final String cnae,
			final Product product,
			final WayPayment wayPayment,
			final TransactionType transactionType,
			final BigDecimal tax) {

		this.mcc = mcc;
		this.cnae = cnae;
		this.product = product;
		this.wayPayment = wayPayment;
		this.transactionType = transactionType;
		this.tax = tax;
	}
}
