package br.com.cafebinario.transactionprocessor.facades;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorStatus;
import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;
import br.com.cafebinario.transactionprocessor.domains.monitor.services.MonitorService;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.TransactionStep;

@Service
public class DefaultMonitorService implements MonitorService {

	private Map<Class<? extends Monitorable>, Map<Long, MonitorEntry<? extends Monitorable>>> monitorPool = Collections
			.synchronizedMap(new HashMap<>());

	@Autowired
	@Qualifier("transactionMonitor")
	private Map<Long, MonitorEntry<?>> transactionMonitor;

	@PostConstruct
	public void setup() {
		monitorPool.put(TransactionStep.class, transactionMonitor);
	}

	@Override
	public <T extends Monitorable> void register(final T target, final Class<T> monitorType) {

		final Map<Long, MonitorEntry<? extends Monitorable>> partition = monitorPool.get(monitorType);

		partition //
				.put(target.getIdentifier(), //
						MonitorEntry //
								.builder() //
								.entry(target) //
								.build());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Monitorable> MonitorEntry<Monitorable> walkOneUp(final Class<T> monitorType, final Long identifier) {

		return (MonitorEntry<Monitorable>) monitorPool //
				.getOrDefault(monitorType, Collections.emptyMap()) //
				.getOrDefault(identifier, MonitorEntry //
						.<Monitorable>builder() //
						.monitorStatus(MonitorStatus.NOT_FOUND) //
						.build());
	}
}
