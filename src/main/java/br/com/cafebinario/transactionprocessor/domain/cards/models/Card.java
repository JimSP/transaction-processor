package br.com.cafebinario.transactionprocessor.domain.cards.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {

	private final String identifier;
	private final String cvv;
	private final LocalDate since;
	private final LocalDate validThru;
	private final String name;
	private final byte[] track1Data;
	private final byte[] track2Data;
	private final byte[] track3Data;
	private final byte[] chipData;
	private final byte[] contactLessData;
	private final Product product;
}
