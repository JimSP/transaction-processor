package br.com.cafebinario.transactionprocessor.domains.monitor.models;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonitorEntry<T extends Monitorable> implements Serializable{

	private static final long serialVersionUID = -8775905855643061734L;

	private final T entry;
	private final MonitorStatus monitorStatus;
}
