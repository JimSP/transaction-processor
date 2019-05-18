package br.com.cafebinario.transactionprocessor.messages.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route implements Serializable{

	private static final long serialVersionUID = -6465741840218766656L;
	private final String routeKey;
	private final String source;
	private final String operation;
	
}
