package br.com.cafebinario.transactionprocessor.functions.dtos.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.functions.dtos.filters.MonitorType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMonitorEntryRequest implements Serializable {

	private static final long serialVersionUID = -6007174300801827156L;

	private final MonitorType monitorType;
	private final Long identifier;

	@JsonCreator
	public GetMonitorEntryRequest( //
			@JsonProperty final MonitorType monitorType, //
			@JsonProperty final Long identifier) {

		this.monitorType = monitorType;
		this.identifier = identifier;
	}
}
