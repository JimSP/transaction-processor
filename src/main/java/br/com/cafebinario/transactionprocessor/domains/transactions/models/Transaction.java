package br.com.cafebinario.transactionprocessor.domains.transactions.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.cardholder.models.CardHolder;
import br.com.cafebinario.transactionprocessor.domains.cards.models.Card;
import br.com.cafebinario.transactionprocessor.domains.commums.SummaryItem;
import br.com.cafebinario.transactionprocessor.domains.stores.models.Store;
import br.com.cafebinario.transactionprocessor.domains.terminals.models.Terminal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Transaction implements SummaryItem, Serializable {

	private static final long serialVersionUID = 4824912830114307370L;

	private final BigDecimal value;
	private final LocalDateTime dateTime;
	private final WayPayment wayPayment;
	private final Card card;
	private final Terminal terminal;
	private final CaptureType captureType;
	private final Store store;
	private final CardHolder cardHolder;
	private final Transaction origialTransaction;
	private final Reason reason;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	public Transaction(
			@JsonProperty final BigDecimal value,
			@JsonProperty final LocalDateTime dateTime,
			@JsonProperty final WayPayment wayPayment,
			@JsonProperty final Card card,
			@JsonProperty final Terminal terminal,
			@JsonProperty final CaptureType captureType,
			@JsonProperty final Store store,
			@JsonProperty final CardHolder cardHolder,
			@JsonProperty final Transaction origialTransaction,
			@JsonProperty final Reason reason) {

		this.value = value;
		this.dateTime = dateTime;
		this.wayPayment = wayPayment;
		this.card = card;
		this.terminal = terminal;
		this.captureType = captureType;
		this.store = store;
		this.cardHolder = cardHolder;
		this.origialTransaction = origialTransaction;
		this.reason = reason;
	}
}
