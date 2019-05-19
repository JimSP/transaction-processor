package br.com.cafebinario.transactionprocessor.functions.exposes;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.ITopic;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.functions.dtos.requests.CreateTransactionRequest;
import br.com.cafebinario.transactionprocessor.messages.dtos.InternalMessage;
import br.com.cafebinario.transactionprocessor.messages.dtos.Route;
import br.com.cafebinario.transactionprocessor.messages.dtos.RouteCatalog;

@Service
public class CreateTransaction implements Consumer<CreateTransactionRequest> {

	private static final String SOURCE = "CreateTransaction";
	private static final String OPERATION = "#transaction:dispacher";

	@Autowired
	@Qualifier("transactionTopic")
	private ITopic<InternalMessage<Transaction>> transactionTopic;

	@Override
	@Transactional
	public void accept(final CreateTransactionRequest createTransactionRequest) {
		dispacher(createTransactionRequest);
	}

	private void dispacher(final CreateTransactionRequest createTransactionRequest) {

		transactionTopic //
				.publish(InternalMessage.<Transaction>builder() //
						.contentData(createTransactionRequest //
								.getTransaction()) //
						.createdDateTime(LocalDateTime.now()) //
						.urlCallback(createTransactionRequest.getUrlCallback()) //
						.route(Route //
								.builder() //
								.routeKey(RouteCatalog.QUEUE_TRANSACTION_ROUTE_KEY) //
								.operation(OPERATION) //
								.source(SOURCE) //
								.build()) //
						.build());
	}
}
