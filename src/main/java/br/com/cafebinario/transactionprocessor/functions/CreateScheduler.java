package br.com.cafebinario.transactionprocessor.functions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.cafebinario.transactionprocessor.domain.settlements.models.Settlement;
import br.com.cafebinario.transactionprocessor.domain.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domain.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.functions.dtos.Between;
import br.com.cafebinario.transactionprocessor.functions.dtos.CreateSettlementRequest;
import br.com.cafebinario.transactionprocessor.functions.dtos.CreateSettlementResponse;
import br.com.cafebinario.transactionprocessor.functions.dtos.SettlementReport;
import br.com.cafebinario.transactionprocessor.functions.dtos.TransactionClassifier;

@Component
public class CreateScheduler implements Function<CreateSettlementRequest, CreateSettlementResponse> {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CreateTransactionClassifier createTransactionClassifier;
	
	@Autowired
	private CreateSummary createSummary;

	@Override
	public CreateSettlementResponse apply(final CreateSettlementRequest createSettlementRequest) {

		validate(createSettlementRequest);

		final LocalDate settlementDate = createSettlementRequest.getSettlementDate();
		final Between between = createSettlementRequest.getBetween();
		final LocalDate from = between.getFrom();
		final LocalDate to = between.getTo();

		final List<Transaction> transactions = transactionService.getTransactions(from, to);

		final List<SettlementReport> settlementReports = transactions //
				.stream() //
				.collect(classifier()) //
				.entrySet() //
				.stream() //
				.map(createSettlementReport(settlementDate, transactions)) //
				.collect(Collectors.toList());

		return CreateSettlementResponse //
				.builder() //
				.settlementReports(settlementReports) //
				.summary(createSummary.apply(transactions)) //
				.build();
	}

	private void validate(final CreateSettlementRequest createSettlementRequest) {

		Assert.notNull(createSettlementRequest, "createSettlementRequest is null.");

		final LocalDate settlementDate = createSettlementRequest.getSettlementDate();

		Assert.notNull(settlementDate, "createSettlementRequest.settlementDate is null.");

		final Between between = createSettlementRequest.getBetween();

		Assert.notNull(between, "createSettlementRequest.between is null.");
	}

	private Collector<Transaction, ?, Map<TransactionClassifier, List<Transaction>>> classifier() {

		return Collectors.groupingBy(createTransactionClassifier);
	}

	private Function<Entry<TransactionClassifier, List<Transaction>>, SettlementReport> createSettlementReport(
			final LocalDate settlementDate, final List<Transaction> transactions) {

		return mapper -> SettlementReport //
				.builder() //
				.transactionClassifier(mapper.getKey()) //
				.settlement(Settlement //
						.builder() //
						.settlementDate(settlementDate) //
						.transactions(transactions)
						.summary(createSummary.apply(mapper.getValue())) //
						.build()) //
				.build();
	}
}
