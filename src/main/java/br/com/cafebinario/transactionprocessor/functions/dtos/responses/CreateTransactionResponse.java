package br.com.cafebinario.transactionprocessor.functions.dtos.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTransactionResponse implements Serializable{
	
	private static final long serialVersionUID = -6872965584915468971L;

	private final Transaction transaction;

	@JsonCreator
	public CreateTransactionResponse(
			@JsonProperty final Transaction transaction) {

		this.transaction = transaction;
	}
}
