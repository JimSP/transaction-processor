package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSettlementRequest implements Serializable {

	private static final long serialVersionUID = -6832772884711276738L;

	@NotNull
	@Positive
	private final Long identifier;

	@NotNull
	@Valid
	private final Between between;

	@NotNull
	private final LocalDate settlementDate;

	@JsonCreator(mode = Mode.PROPERTIES)
	public CreateSettlementRequest(@JsonProperty final Long identifier, @JsonProperty final Between between,
			@JsonProperty final LocalDate settlementDate) {

		this.identifier = identifier;
		this.between = between;
		this.settlementDate = settlementDate;
	}
}
