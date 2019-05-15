package br.com.cafebinario.transactionprocessor.domains.terminals.models;

import java.io.Serializable;

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

}
