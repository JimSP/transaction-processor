package br.com.cafebinario.transactionprocessor.domains.registers.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document implements Serializable {


	private static final long serialVersionUID = -1053780423248049585L;

	private final String value;
	private final String issuerInstituition;
	private final LocalDate issuerDate;
	private final LocalDate expirateDate;
	private final DocumentType documentType;
	
	@JsonCreator(mode=Mode.PROPERTIES)
	public Document(
			@JsonProperty final String value,
			@JsonProperty final String issuerInstituition,
			@JsonProperty final LocalDate issuerDate,
			@JsonProperty final LocalDate expirateDate,
			@JsonProperty final DocumentType documentType) {

		this.value = value;
		this.issuerInstituition = issuerInstituition;
		this.issuerDate = issuerDate;
		this.expirateDate = expirateDate;
		this.documentType = documentType;
	}
}
