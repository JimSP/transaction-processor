package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public final class FunctionOutput  extends FunctionHandlePart {

	private static final long serialVersionUID = 2244067876839390993L;

	@Builder
	@JsonCreator(mode=Mode.PROPERTIES)
	public FunctionOutput(
			@JsonProperty final String type, 
			@JsonProperty final String example) {
		super(type, example);
	}

}
