package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleReport implements Serializable {

	private static final long serialVersionUID = -9025672279165139259L;
	
	private final Settlement settlement;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public ScheduleReport(
			@JsonProperty final Settlement settlement) {

		this.settlement = settlement;
	}
}
