package br.com.cafebinario.transactionprocessor.domains.terminals.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Terminal implements Serializable {

	private static final long serialVersionUID = 8885279425719945098L;

	private final Long identifier;
	private final String model;
	private final String serialNumber;
	private final TerminalType terminalType;
	private final byte[] deviceData;
	
	@JsonCreator
	public Terminal(
			@JsonProperty final Long identifier,
			@JsonProperty final String model,
			@JsonProperty final String serialNumber,
			@JsonProperty final TerminalType terminalType,
			@JsonProperty final byte[] deviceData) {

		this.identifier = identifier;
		this.model = model;
		this.serialNumber = serialNumber;
		this.terminalType = terminalType;
		this.deviceData = deviceData;
	}
}
