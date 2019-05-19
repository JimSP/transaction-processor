package br.com.cafebinario.transactionprocessor.functions.internal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.functions.dtos.filters.Between;
import br.com.cafebinario.transactionprocessor.functions.dtos.filters.TransactionClassifier;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.ScheduleReport;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateScheduleRequest;
import br.com.cafebinario.transactionprocessor.functions.dtos.responses.CreateScheduleResponse;
import br.com.cafebinario.transactionprocessor.functions.exposes.CreateScheduleReport;
import br.com.cafebinario.transactionprocessor.functions.exposes.CreateSummary;
import br.com.cafebinario.transactionprocessor.functions.exposes.CreateTransactionClassifier;

@Service
public class CreateSchedulerCacheable implements Function<CreateScheduleRequest, CreateScheduleResponse> {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CreateSummary createSummary;
	
	@Autowired
	private CreateScheduleReport createScheduleReport;
	
	@Autowired
	private CreateTransactionClassifier createTransactionClassifier;
	
	@Override
	public CreateScheduleResponse apply(final CreateScheduleRequest createScheduleRequest) {
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
	
	private Collector<Transaction, ?, Map<TransactionClassifier, List<Transaction>>> classifier() {

		return Collectors.groupingBy(createTransactionClassifier);
	}
}
