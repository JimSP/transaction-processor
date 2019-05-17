package br.com.cafebinario.transactionprocessor.domains.advances.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Advance implements Serializable {

	private static final long serialVersionUID = 7829858189213990658L;

	private final Long identifier;
	private final LocalDate advanceDate;
	private final List<Settlement> settlements;

	@JsonCreator(mode=Mode.PROPERTIES)
	public Advance(
			@JsonProperty final Long identifier,
			@JsonProperty final LocalDate advanceDate,
			@JsonProperty final List<Settlement> settlements) {

		this.identifier = identifier;
		this.advanceDate = advanceDate;
		this.settlements = settlements;
	}
}
