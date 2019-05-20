package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class AttributeDefinition implements Serializable{

	private static final long serialVersionUID = 4962113562925194496L;

	private final String name;
	private final String type;

	@JsonCreator
	public AttributeDefinition(
			@JsonProperty final String name,
			@JsonProperty final String type) {

		this.name = name;
		this.type = type;
	}
}
