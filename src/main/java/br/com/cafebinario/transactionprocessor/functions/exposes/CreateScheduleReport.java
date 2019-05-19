package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.ScheduleReport;

@Service
public class CreateScheduleReport
		implements BiFunction<List<Transaction>, LocalDate, ScheduleReport> {

	@Autowired
	private CreateSummary createSummary;

	@Override
	public ScheduleReport apply(final List<Transaction> transactions,
			final LocalDate settlementDate) {

		return ScheduleReport //
				.builder() //
				.settlement(Settlement //
						.builder() //
						.settlementDate(settlementDate) //
						.summary(createSummary //
								.apply(transactions)) //
						.transactions(transactions) //
						.build()) //
				.build();
	}
}
