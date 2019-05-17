package br.com.cafebinario.transactionprocessor.domains.payments.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.commums.Moviment;
import br.com.cafebinario.transactionprocessor.domains.commums.Rubric;
import br.com.cafebinario.transactionprocessor.domains.registers.models.BankAccount;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class Payment extends Moviment {

	private static final long serialVersionUID = -1602297414528452903L;

	private final BankAccount beneficiary;
	private final PaymentType paymentType;

	@Builder
	@JsonCreator(mode = Mode.PROPERTIES)
	public Payment(
			@JsonProperty final Long identifier,
			@JsonProperty final BigDecimal value,
			@JsonProperty final Rubric rubric,
			@JsonProperty final String description,
			@JsonProperty final BankAccount beneficiary,
			@JsonProperty final PaymentType paymentType) {
		
		super(identifier, value, rubric, description);

		this.beneficiary = beneficiary;
		this.paymentType = paymentType;
	}
}
