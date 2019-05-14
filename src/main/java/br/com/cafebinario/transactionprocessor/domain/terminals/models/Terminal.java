package br.com.cafebinario.transactionprocessor.domain.terminals.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Terminal {

	private final Long identifier;
	private final String model;
	private final String serialNumber;
	private final TerminalType terminalType;
	private final byte[] deviceData;
	
}
