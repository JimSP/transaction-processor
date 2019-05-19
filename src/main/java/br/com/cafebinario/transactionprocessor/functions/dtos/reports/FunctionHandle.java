package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class FunctionHandle {

	private final String path;
	private final HttpMethod httpMethod;
	
	@JsonCreator
	public FunctionHandle(
			@JsonProperty final String path,
			@JsonProperty final HttpMethod httpMethod) {

		this.path = path;
		this.httpMethod = httpMethod;
	}
}
