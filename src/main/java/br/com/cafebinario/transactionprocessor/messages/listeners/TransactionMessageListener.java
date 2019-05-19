package br.com.cafebinario.transactionprocessor.messages.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.IQueue;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.messages.dtos.InternalMessage;
import br.com.cafebinario.transactionprocessor.messages.dtos.Route;
import br.com.cafebinario.transactionprocessor.messages.exceptions.OfferMessageException;
import br.com.cafebinario.transactionprocessor.messages.pool.TransactionQueuePool;

@Component
public class TransactionMessageListener implements MessageListener<InternalMessage<Transaction>> {

	@Autowired
	private TransactionQueuePool transactionQueuePool;

	@Override
	@Transactional
	public void onMessage(final Message<InternalMessage<Transaction>> message) {

		final InternalMessage<Transaction> internalMessage = message.getMessageObject();

		final Transaction transaction = internalMessage.getContentData();
		final Route route = internalMessage.getRoute();
		final String routeKey = route.getRouteKey();

		transactionQueuePool //
				.getQueuePool() //
				.get(routeKey) //
				.stream() //
				.forEach(queue -> sendQueue(transaction, queue));
	}

	private void sendQueue(final Transaction transaction, final IQueue<Transaction> queue) {
		try {
			queue.put(transaction);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw OfferMessageException.of(e).get();
		}
	}
}
