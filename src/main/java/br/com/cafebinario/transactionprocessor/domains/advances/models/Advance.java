package br.com.cafebinario.transactionprocessor.domains.advances.models;

import java.util.List;

import br.com.cafebinario.transactionprocessor.domains.settlements.models.Settlement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Advance {

	private final Long identifier;
	private final List<Settlement> settlements;
}
