package br.com.cafebinario.transactionprocessor.functions.dtos.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CreateTransactionRequest implements Serializable{
	private static final long serialVersionUID = 2195298451086799392L;
	
	private final Transaction transaction;
	private final String urlCallback;

	@JsonCreator
	public CreateTransactionRequest(
			@JsonProperty final Transaction transaction, 
			@JsonProperty final String urlCallback) {

		this.transaction = transaction;
		this.urlCallback = urlCallback;
	}
}
