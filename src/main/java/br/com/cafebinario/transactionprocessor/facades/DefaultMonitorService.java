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

		final Map<Long, MonitorEntry<? extends Monitorable>> partition = getPartition(monitorType);
		
		partition //
				.put(target.getIdentifier(), //
						MonitorEntry //
								.builder() //
								.entry(target) //
								.monitorStatus(MonitorStatus.WAITING) //
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

	@Override
	public <T extends Monitorable> void emitProcessing(final Class<T> monitorType, final Long identifier) {

		updateMonitorStatus(monitorType, identifier, MonitorStatus.PROCESSING);
	}
	
	@Override
	public <T extends Monitorable> void emitOk(final Class<T> monitorType, final Long identifier) {
		
		updateMonitorStatus(monitorType, identifier, MonitorStatus.OK);
	}

	@Override
	public <T extends Monitorable> void emitError(final Class<T> monitorType, final Long identifier) {
		
		updateMonitorStatus(monitorType, identifier, MonitorStatus.ERROR);
	}

	@Override
	public <T extends Monitorable> void unregister(final Class<T> monitorType, final Long identifier) {
		
		final Map<Long, MonitorEntry<? extends Monitorable>> partition = getPartition(monitorType);
		
		partition.remove(identifier);
	}

	@Override
	public <T extends Monitorable> void clean(final Class<T> monitorType) {
		
		final Map<Long, MonitorEntry<? extends Monitorable>> partition = getPartition(monitorType);
		
		partition.clear();
	}

	@Override
	public void clean() {
		
		monitorPool.clear();
	}
	
	private <T extends Monitorable> Map<Long, MonitorEntry<? extends Monitorable>> getPartition(
			final Class<T> monitorType) {
		
		return monitorPool.get(monitorType);
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Monitorable> void updateMonitorStatus(final Class<T> monitorType, final Long identifier, final MonitorStatus monitorStatus) {

		final MonitorEntry<Monitorable> monitorEntry = walkOneUp(monitorType, identifier) //
				.toBuilder() //
				.monitorStatus(monitorStatus) //
				.build();
		
		register((T) monitorEntry.getEntry(), monitorType);
	}
}
