package br.com.cafebinario.transactionprocessor.functions.dtos.responses;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.cafebinario.transactionprocessor.functions.dtos.reports.ScheduleReport;
import br.com.cafebinario.transactionprocessor.functions.dtos.reports.Summary;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CreateScheduleResponse implements Serializable {

	private static final long serialVersionUID = 6102842437248061573L;

	private final List<ScheduleReport> scheduleReports;
	private final Summary summary;
	
	@JsonCreator(mode = Mode.PROPERTIES)
	private CreateScheduleResponse(final List<ScheduleReport> scheduleReports, final Summary summary) {

		this.scheduleReports = scheduleReports;
		this.summary = summary;
	}
}
