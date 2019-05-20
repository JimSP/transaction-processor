package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ObjectDefinition implements Serializable{

	private static final long serialVersionUID = 975371220083525319L;
	
	private final List<AttributeDefinition> attributes;

	@JsonCreator
	public ObjectDefinition(@JsonProperty final List<AttributeDefinition> attributes) {
	
		this.attributes = attributes;
	}
}
