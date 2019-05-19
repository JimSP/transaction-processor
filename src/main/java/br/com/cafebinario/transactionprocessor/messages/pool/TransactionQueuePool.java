package br.com.cafebinario.transactionprocessor.messages.pool;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hazelcast.core.IQueue;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.messages.dtos.RouteCatalog;

@Component
public class TransactionQueuePool {
	
	private final Map<String, List<IQueue<Transaction>>> queuePool = Collections.synchronizedMap(new HashMap<>());

	@Autowired
	@Qualifier("transactionQueue")
	private IQueue<Transaction> transactionQueue;
	
	@Autowired
	@Qualifier("monitoringTransactionQueue")
	private IQueue<Transaction> monitoringTransactionQueue;
	
	@PostConstruct
	public void init() {
		queuePool.put(RouteCatalog.QUEUE_TRANSACTION_ROUTE_KEY,
				Collections.synchronizedList(Arrays.asList(transactionQueue, monitoringTransactionQueue)));
	}

	public Map<String, List<IQueue<Transaction>>> getQueuePool() {
		return queuePool;
	}
}
