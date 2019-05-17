package br.com.cafebinario.transactionprocessor.domains.cards.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Card implements Serializable{

	private static final long serialVersionUID = 191430167693020543L;

	private final String identifier;
	private final String cvv;
	private final LocalDate since;
	private final LocalDate validThru;
	private final String name;
	private final String number;
	private final byte[] track1Data;
	private final byte[] track2Data;
	private final byte[] track3Data;
	private final byte[] chipData;
	private final byte[] contactLessData;
	private final Product product;

	@JsonCreator(mode=Mode.PROPERTIES)
	public Card(
			@JsonProperty final String identifier,
			@JsonProperty final String cvv,
			@JsonProperty final LocalDate since,
			@JsonProperty final LocalDate validThru,
			@JsonProperty final String name,
			@JsonProperty final String number,
			@JsonProperty final byte[] track1Data,
			@JsonProperty final byte[] track2Data,
			@JsonProperty final byte[] track3Data,
			@JsonProperty final byte[] chipData,
			@JsonProperty final byte[] contactLessData,
			@JsonProperty final Product product) {

		this.identifier = identifier;
		this.cvv = cvv;
		this.since = since;
		this.validThru = validThru;
		this.name = name;
		this.number = number;
		this.track1Data = track1Data;
		this.track2Data = track2Data;
		this.track3Data = track3Data;
		this.chipData = chipData;
		this.contactLessData = contactLessData;
		this.product = product;
	}
}
