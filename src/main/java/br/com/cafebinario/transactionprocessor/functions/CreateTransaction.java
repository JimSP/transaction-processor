package br.com.cafebinario.transactionprocessor.functions;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateTransactionRequest;

@Service
public class CreateTransaction implements Consumer<CreateTransactionRequest> {

	@Autowired
	@Qualifier("hazelcastExecutorService")
	private ExecutorService hazelcastExecutorService;

	@Autowired
	private TransactionService transactionService;

	@Override
	public void accept(final CreateTransactionRequest createTransactionRequest) {

		final Transaction transaction = createTransactionRequest.getTransaction();
		
		dispacher(transaction);

		monitoring(transaction);
	}

	private void dispacher(final Transaction transaction) {

		hazelcastExecutorService //
				.submit(() -> transactionService //
						.create(transaction));
	}

	private void monitoring(final Transaction transaction) {

		transactionService.monitoring(transaction);
	}
}
