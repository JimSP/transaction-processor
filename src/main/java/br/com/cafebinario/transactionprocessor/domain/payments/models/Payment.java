package br.com.cafebinario.transactionprocessor.domain.payments.models;

import java.math.BigDecimal;

import br.com.cafebinario.transactionprocessor.domain.registers.models.BankAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

	private final Long identifier;
	private final BigDecimal value;
	private final BankAccount beneficiary;
	private final String description;
	private final PaymentType paymentType;
}
