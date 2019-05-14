package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSettlementResponse {
	
	private final List<SettlementReport> settlementReports;
	private final Summary summary;
}
