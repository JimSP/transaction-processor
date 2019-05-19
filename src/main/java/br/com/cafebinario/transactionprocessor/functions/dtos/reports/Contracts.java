package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Contracts {

	private final List<FunctionContract> functions;
	private final List<FunctionContract> consumes;
	private final List<FunctionContract> produces;

	@JsonCreator(mode=Mode.PROPERTIES)
	public Contracts(
			@JsonProperty final List<FunctionContract> functions,
			@JsonProperty final List<FunctionContract> consumes,
			@JsonProperty final List<FunctionContract> produces) {

		this.functions = functions;
		this.consumes = consumes;
		this.produces = produces;
	}
}
