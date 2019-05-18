package br.com.cafebinario.transactionprocessor.configurations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ITopic;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.messages.dtos.InternalMessage;
import br.com.cafebinario.transactionprocessor.messages.dtos.RouteCatalog;
import br.com.cafebinario.transactionprocessor.messages.listeners.TransactionMessageListener;

@Configuration
@EnableHazelcastRepositories(basePackages = "br.com.cafebinario.transactionprocessor.hazelcast.repositories")
@EnableCaching
public class HazelcastConfiguration {

	private static final String MONITORING_QUEUE = "monitoring-queue";
	private static final String TRANSACTION_QUEUE = "transaction-queue";
	@Autowired
	private TransactionMessageListener transactionMessageListener;

	@Bean(destroyMethod = "shutdown", name = "hazelcastInstance")
	public HazelcastInstance hazelcastInstance(@Autowired final Config config) {

		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public Config config() {

		return new Config("transaction-processor") //
				.addMapConfig(mapConfigCache()) //
				.addMapConfig(mapConfigMonitor()) //
				.addExecutorConfig(executorConfig()) //
				.addTopicConfig(topicConfig()).addQueueConfig(createTransactionqueueConfig())
				.addQueueConfig(createMonitoringQueueConfig());
	}

	@Bean("hazelcastExecutorService")
	public ExecutorService hazelcastExecutorService(final @Autowired HazelcastInstance hazelcastInstance) {

		return hazelcastInstance.getExecutorService("executor");
	}

	@Bean("transactionMonitor")
	public Map<Long, MonitorEntry<Transaction>> transactionMonitor(
			final @Autowired HazelcastInstance hazelcastInstance) {

		return hazelcastInstance.getMap("transaction-monitor");
	}

	@Bean("transactionTopic")
	public ITopic<InternalMessage<Transaction>> transactionTopic(final @Autowired HazelcastInstance hazelcastInstance) {

		return hazelcastInstance //
				.getTopic("transaction-topic");
	}

	@Bean("transactionQueue")
	public IQueue<Transaction> transactionQueue(final @Autowired HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getQueue(TRANSACTION_QUEUE);
	}

	@Bean("monitoringTransactionQueue")
	public IQueue<Transaction> monitoringTransactionQueue(final @Autowired HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getQueue(MONITORING_QUEUE);
	}

	@Bean
	public Map<String, List<IQueue<Transaction>>> transactionQueuesMap(final @Autowired HazelcastInstance hazelcastInstance) {
		
		final IQueue<Transaction> transactionQueue = hazelcastInstance.getQueue(TRANSACTION_QUEUE);
		final IQueue<Transaction> monitoringQueue = hazelcastInstance.getQueue(MONITORING_QUEUE);
		
		final Map<String, List<IQueue<Transaction>>> queuePool = Collections.synchronizedMap(new HashMap<>());
		
		queuePool.put(RouteCatalog.QUEUE_TRANSACTION_ROUTE_KEY,
				Collections.synchronizedList(Arrays.asList(transactionQueue, monitoringQueue)));
		
		return queuePool;
	}

	private MapConfig mapConfigCache() {

		return new MapConfig() //
				.setName("cache") //
				.setMaxSizeConfig(
						new MaxSizeConfig(MaxSizeConfig.DEFAULT_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
				.setEvictionPolicy(EvictionPolicy.LRU) //
				.setTimeToLiveSeconds(20);
	}

	private MapConfig mapConfigMonitor() {

		return new MapConfig() //
				.setName("transaction-monitor") //
				.setMaxSizeConfig(
						new MaxSizeConfig(MaxSizeConfig.DEFAULT_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
				.setEvictionPolicy(EvictionPolicy.LRU) //
				.setTimeToLiveSeconds(20);
	}

	private ExecutorConfig executorConfig() {

		return new ExecutorConfig("executor") //
				.setPoolSize(100) //
				.setQueueCapacity(Integer.MAX_VALUE);
	}

	private TopicConfig topicConfig() {

		return new TopicConfig("transaction-topic") //
				.setMultiThreadingEnabled(Boolean.TRUE)
				.addMessageListenerConfig(new ListenerConfig(transactionMessageListener));
	}

	private QueueConfig createTransactionqueueConfig() {
		return new QueueConfig(TRANSACTION_QUEUE) //
				.setMaxSize(Integer.MAX_VALUE);
	}

	private QueueConfig createMonitoringQueueConfig() {
		return new QueueConfig(MONITORING_QUEUE) //
				.setMaxSize(Integer.MAX_VALUE);
	}
}
