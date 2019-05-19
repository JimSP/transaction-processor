package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class FunctionHandlePart implements Serializable{

	private static final long serialVersionUID = -1665211203654963380L;

	private final String type;
	private final String example;

}
