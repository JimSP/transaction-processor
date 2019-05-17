package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSettlementResponse implements Serializable {

	private static final long serialVersionUID = 6102842437248061573L;

	private final List<SettlementReport> settlementReports;
	private final Summary summary;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	private CreateSettlementResponse(final List<SettlementReport> settlementReports, final Summary summary) {

		this.settlementReports = settlementReports;
		this.summary = summary;
	}
}
