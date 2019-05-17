package br.com.cafebinario.transactionprocessor.domains.commums;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public abstract class Moviment implements Serializable{
	
	private static final long serialVersionUID = -1985815094548364173L;

	private final Long identifier;
	private final BigDecimal value;
	private final Rubric rubric;
	private final String description;
}
