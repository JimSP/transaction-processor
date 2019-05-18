package br.com.cafebinario.transactionprocessor.domains.monitor.services;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.monitor.models.Monitorable;

public interface MonitorService {

	<T extends Monitorable> void register(final T target, final Class<T> monitorType);

	<T extends Monitorable> MonitorEntry<Monitorable> walkOneUp(final Class<T> monitorType, final Long identifier);

	<T extends Monitorable> void emitProcessing(final Class<T> monitorType, final Long identifier);

	<T extends Monitorable> void emitOk(final Class<T> monitorType, final Long identifier);

	<T extends Monitorable> void emitError(final Class<T> monitorType, final Long identifier);
	
	<T extends Monitorable> void unregister(final Class<T> monitorType, final Long identifier);
	
	<T extends Monitorable> void clean(final Class<T> monitorType);
	
	void clean();
}
