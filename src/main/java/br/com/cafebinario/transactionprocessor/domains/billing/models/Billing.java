package br.com.cafebinario.transactionprocessor.domains.billing.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.cafebinario.transactionprocessor.domains.commums.Moviment;
import br.com.cafebinario.transactionprocessor.domains.commums.Rubric;
import br.com.cafebinario.transactionprocessor.domains.registers.models.Person;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class Billing extends Moviment {

	private static final long serialVersionUID = -8377000417091407813L;

	private final Person debtor;
	private final CollectType collectType;

	@Builder
	@JsonCreator(mode=Mode.PROPERTIES)
	public Billing(
			final Long identifier,
			final BigDecimal value,
			final Rubric rubric,
			final String description,
			final Person debtor,
			final CollectType collectType) {

		super(identifier, value, rubric, description);

		this.debtor = debtor;
		this.collectType = collectType;
	}
}
