package br.com.cafebinario.transactionprocessor.domain.transactions.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.cafebinario.transactionprocessor.domain.cardholder.models.CardHolder;
import br.com.cafebinario.transactionprocessor.domain.cards.models.Card;
import br.com.cafebinario.transactionprocessor.domain.cards.models.Product;
import br.com.cafebinario.transactionprocessor.domain.stores.models.Store;
import br.com.cafebinario.transactionprocessor.domain.terminals.models.Terminal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

	private final BigDecimal value;
	private final LocalDateTime dateTime;
	private final WayPayment wayPayment;
	private final Card card;
	private final Terminal terminal;
	private final Store store;
	private final CardHolder cardHolder;
	private final Transaction origialTransaction;
	private final Reason reason;
	private final Product product;
}
