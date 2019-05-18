package br.com.cafebinario.transactionprocessor.messages.worker;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;

@Component
public class TransactionQueueWorker implements Runnable, AutoCloseable {

	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Autowired
	private TransactionService transactionService;

	private AtomicBoolean started = new AtomicBoolean(Boolean.TRUE);

	@Override
	public void run() {

		final IQueue<Transaction> transactions = hazelcastInstance.getQueue("transaction-queue");

		while (started.get()) {
			try {
				final Transaction transaction = transactions.take();
				transactionService.create(transaction);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public void close() throws Exception {
		started.set(Boolean.TRUE);
	}
}
