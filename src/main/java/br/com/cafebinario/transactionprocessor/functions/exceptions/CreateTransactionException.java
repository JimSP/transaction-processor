package br.com.cafebinario.transactionprocessor.functions.exceptions;

import com.hazelcast.util.function.Supplier;

import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateTransactionRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateTransactionException extends RuntimeException {

	private static final long serialVersionUID = 209678151163873657L;

	public static Supplier<CreateTransactionException> createTransactionExceptionSupplier(
			final CreateTransactionRequest createTransactionRequest) {

		return () -> new CreateTransactionException(createTransactionRequest);
	}

	private final CreateTransactionRequest createTransactionRequest;
}
