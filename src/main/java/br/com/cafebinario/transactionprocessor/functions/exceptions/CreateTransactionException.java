package br.com.cafebinario.transactionprocessor.functions.exceptions;

import com.hazelcast.util.function.Supplier;

import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateTransactionRequest;
import lombok.Getter;

@Getter
public final class CreateTransactionException extends RuntimeException {

	private static final long serialVersionUID = 209678151163873657L;

	public static Supplier<CreateTransactionException> of(
			final CreateTransactionRequest createTransactionRequest) {

		return () -> new CreateTransactionException(createTransactionRequest);
	}

	private final CreateTransactionRequest createTransactionRequest;

	public CreateTransactionException(final CreateTransactionRequest createTransactionRequest) {
		super("transaction not created.");
		this.createTransactionRequest = createTransactionRequest;
	}
	
	
}
