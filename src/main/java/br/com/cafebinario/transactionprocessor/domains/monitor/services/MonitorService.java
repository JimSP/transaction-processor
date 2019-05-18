package br.com.cafebinario.transactionprocessor.domains.monitor.services;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;

public interface MonitorService {

	<T extends Monitorable> void register(final T target, final Class<T> monitorType);
	<T extends Monitorable> MonitorEntry<Monitorable> walkOneUp(final Class<T> monitorType, final Long identifier);
}
