package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.cafebinario.transactionprocessor.functions.dtos.filters.Between;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateScheduleRequest;
import br.com.cafebinario.transactionprocessor.functions.dtos.responses.CreateScheduleResponse;
import br.com.cafebinario.transactionprocessor.functions.internal.CreateSchedulerCacheable;

@Service
public class CreateScheduler implements Function<CreateScheduleRequest, CreateScheduleResponse> {
	
	@Autowired
	private CreateSchedulerCacheable createSchedulerCacheable;

	@Override
	public CreateScheduleResponse apply(final CreateScheduleRequest createScheduleRequest) {

		validate(createScheduleRequest);
		
		return createSchedulerCacheable.apply(createScheduleRequest);
	}

	private void validate(final CreateScheduleRequest createScheduleRequest) {

		Assert.notNull(createScheduleRequest, "createSettlementRequest is null.");

		final LocalDate settlementDate = createScheduleRequest.getSettlementDate();

		Assert.notNull(settlementDate, "createSettlementRequest.settlementDate is null.");

		final Between between = createScheduleRequest.getBetween();

		Assert.notNull(between, "createSettlementRequest.between is null.");
	}
}
