package br.com.cafebinario.transactionprocessor.functions.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Between implements Serializable {
	
	private static final long serialVersionUID = 1708931288865525119L;

	@NotNull
	private LocalDate from;

	@NotNull
	private LocalDate to;

	@JsonCreator(mode = Mode.PROPERTIES)
	public Between(final LocalDate from, final LocalDate to) {

		this.from = from;
		this.to = to;
	}
}
