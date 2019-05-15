package br.com.cafebinario.transactionprocessor.domains.collect.models;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Collect {

	private final CollectType collectType;
}
