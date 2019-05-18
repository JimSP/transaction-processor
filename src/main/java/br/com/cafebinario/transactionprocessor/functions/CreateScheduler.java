package br.com.cafebinario.transactionprocessor.functions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.functions.dtos.filters.Between;
import br.com.cafebinario.transactionprocessor.functions.dtos.filters.TransactionClassifier;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.ScheduleReport;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateScheduleRequest;
import br.com.cafebinario.transactionprocessor.functions.dtos.responses.CreateScheduleResponse;

@Service
public class CreateScheduler implements Function<CreateScheduleRequest, CreateScheduleResponse> {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CreateTransactionClassifier createTransactionClassifier;
	
	@Autowired
	private CreateSummary createSummary;
	
	@Autowired
	private CreateScheduleReport createScheduleReport;

	@Override
	@Cacheable
	public CreateScheduleResponse apply(final CreateScheduleRequest createScheduleRequest) {

		validate(createScheduleRequest);

		final LocalDate settlementDate = createScheduleRequest.getSettlementDate();
		final Between between = createScheduleRequest.getBetween();
		final LocalDate from = between.getFrom();
		final LocalDate to = between.getTo();

		final List<Transaction> transactions = transactionService.getTransactions(from, to);

		final List<ScheduleReport> scheduleReports = transactions //
				.stream() //
				.collect(classifier()) //
				.entrySet() //
				.stream() //
				.map(entry -> createScheduleReport.apply(entry.getValue(), settlementDate)) //
				.collect(Collectors.toList());

		return CreateScheduleResponse //
				.builder() //
				.scheduleReports(scheduleReports) //
				.summary(createSummary.apply(transactions)) //
				.build();
	}

	private void validate(final CreateScheduleRequest createScheduleRequest) {

		Assert.notNull(createScheduleRequest, "createSettlementRequest is null.");

		final LocalDate settlementDate = createScheduleRequest.getSettlementDate();

		Assert.notNull(settlementDate, "createSettlementRequest.settlementDate is null.");

		final Between between = createScheduleRequest.getBetween();

		Assert.notNull(between, "createSettlementRequest.between is null.");
	}

	private Collector<Transaction, ?, Map<TransactionClassifier, List<Transaction>>> classifier() {

		return Collectors.groupingBy(createTransactionClassifier);
	}
}
