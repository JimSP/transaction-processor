package br.com.cafebinario.transactionprocessor.domains.commums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rubric {

	BILLING(RubricType.ACCOUNTING), PAYMENT(RubricType.ACCOUNTING), FISCAL(RubricType.MANAGEMENT);

	private final RubricType rubricType;
}
