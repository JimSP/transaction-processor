package br.com.cafebinario.transactionprocessor.domains.cards.models;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Product {

	VISA_CREDIT(TransactionType.CREDIT),
	VISA_DEBIT(TransactionType.DEBIT),
	VISA_ELECTRON(TransactionType.DEBIT),
	MASTERCARD(TransactionType.CREDIT),
	MAESTRO(TransactionType.DEBIT),
	VALE_REFEICAO(TransactionType.DEBIT),
	VALE_ALIMENTACAO(TransactionType.DEBIT),
	ELO_CREDITO(TransactionType.CREDIT),
	ELO_DEBITO(TransactionType.DEBIT),
	TICKET_REFEICAO(TransactionType.DEBIT),
	TICKET_ALIMENTACAO(TransactionType.DEBIT),
	SODEXO_ALIMENTACAO(TransactionType.DEBIT),
	SODEXO_REFEICAO(TransactionType.DEBIT),
	ALELO_REFEICAO(TransactionType.DEBIT),
	ALELO_ALIMENTACAO(TransactionType.DEBIT),
	VALE_TRANSAPORTE(TransactionType.DEBIT),
	GIFT_CARD(TransactionType.DEBIT),
	PRIVATE_LABEL(TransactionType.DEBIT);
	
	private final TransactionType transactionType;
}
