package br.com.cafebinario.transactionprocessor.domains.monitor.models;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Monitor<T extends Monitorable> implements Serializable {

	private static final long serialVersionUID = 4415834720155719573L;

	private final HashMap<Long, MonitorEntry<T>> targets;
}
