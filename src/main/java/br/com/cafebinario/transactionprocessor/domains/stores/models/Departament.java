package br.com.cafebinario.transactionprocessor.domains.stores.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.terminals.models.Terminal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Departament implements Serializable{

	private static final long serialVersionUID = 694475862292385291L;

	private final Long identifier;
	private final List<Terminal> terminals;
	private final String cnae;
	private final String mcc;
	
	@JsonCreator
	public Departament(
			@JsonProperty final Long identifier,
			@JsonProperty final List<Terminal> terminals,
			@JsonProperty final String cnae,
			@JsonProperty final String mcc) {

		this.identifier = identifier;
		this.terminals = terminals;
		this.cnae = cnae;
		this.mcc = mcc;
	}
	
	
}
