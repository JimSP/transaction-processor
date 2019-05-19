package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class FunctionContract {

	private final List<FunctionHandle> handles;
	private final FunctionInput input;
	private final FunctionOutput output;
	
	@JsonCreator
	public FunctionContract(
			@JsonProperty final List<FunctionHandle> handles,
			@JsonProperty final FunctionInput input,
			@JsonProperty final FunctionOutput output) {

		this.handles = handles;
		this.input = input;
		this.output = output;
	}
}
