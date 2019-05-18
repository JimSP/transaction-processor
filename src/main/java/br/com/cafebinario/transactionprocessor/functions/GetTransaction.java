package br.com.cafebinario.transactionprocessor.functions;

import java.util.function.LongFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.functions.dtos.responses.CreateTransactionResponse;

@Service
public class GetTransaction implements LongFunction<CreateTransactionResponse> {

	@Autowired
	private TransactionService transactionService;

	@Override
	public CreateTransactionResponse apply(final long identifier) {
		
		return CreateTransactionResponse //
				.builder() //
				.transaction(transactionService.getTransaction(identifier)) //
				.build();
	}
}
