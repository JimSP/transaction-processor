package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;
import br.com.cafebinario.transactionprocessor.domains.monitor.services.MonitorService;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.GetMonitorEntryRequest;

@Service
public class GetMonitorEntry implements Function<GetMonitorEntryRequest, MonitorEntry<Monitorable>> {

	@Autowired
	private MonitorService monitorService;

	@Override
	public MonitorEntry<Monitorable> apply(final GetMonitorEntryRequest getMonitorEntryRequest) {
		Assert.notNull(getMonitorEntryRequest, "getMonitorEntryRequest is null");
		Assert.notNull(getMonitorEntryRequest.getMonitorType(), "getMonitorEntryRequest.monitorType is null");

		return monitorService.walkOneUp(getMonitorEntryRequest.getMonitorType().getType(),
				getMonitorEntryRequest.getIdentifier());
	}
}
