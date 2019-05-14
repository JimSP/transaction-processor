package br.com.cafebinario.transactionprocessor.domain.settlements.models;

import java.time.LocalDate;
import java.util.List;

import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.Summary;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Settlement {

	private final Long identifier;
	private final List<Transaction> transactions;
	private final LocalDate settlementDate;
	private final Summary summary;
}
