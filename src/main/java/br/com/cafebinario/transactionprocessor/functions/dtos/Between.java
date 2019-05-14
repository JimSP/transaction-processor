package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Between {

	@NotNull
	private LocalDate from;

	@NotNull
	private LocalDate to;

	@JsonCreator
	public Between(final LocalDate from, final LocalDate to) {

		this.from = from;
		this.to = to;
	}
}
