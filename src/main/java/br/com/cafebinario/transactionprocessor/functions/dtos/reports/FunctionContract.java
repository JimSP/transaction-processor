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
	private final AttributeDefinition input;
	private final AttributeDefinition output;
	
	@JsonCreator
	public FunctionContract(
			@JsonProperty final List<FunctionHandle> handles,
			@JsonProperty final AttributeDefinition input,
			@JsonProperty final AttributeDefinition output) {

		this.handles = handles;
		this.input = input;
		this.output = output;
	}
}
