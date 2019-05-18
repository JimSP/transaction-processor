package br.com.cafebinario.transactionprocessor.configurations;

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
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import br.com.cafebinario.transactionprocessor.domains.monitor.models.MonitorEntry;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;

@Configuration
@EnableHazelcastRepositories(basePackages = "br.com.cafebinario.transactionprocessor.hazelcast.repositories")
@EnableCaching
public class HazelcastConfiguration {

	@Bean(destroyMethod = "shutdown", name = "hazelcastInstance")
	public HazelcastInstance hazelcastInstance(@Autowired final Config config) {
		
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public Config config() {
		
		return new Config("transaction-processor") //
				.addMapConfig(mapConfigCache()) //
				.addMapConfig(mapConfigMonitor()) //
				.addExecutorConfig(executorConfig());
	}
	
	@Bean("hazelcastExecutorService")
	public ExecutorService hazelcastExecutorService(final @Autowired HazelcastInstance hazelcastInstance) {
		
		return hazelcastInstance.getExecutorService("executor");
	}
	
	@Bean("transactionMonitor")
	public Map<Long, MonitorEntry<Transaction>> transactionMonitor(final @Autowired HazelcastInstance hazelcastInstance){

		return hazelcastInstance.getMap("transaction-monitor");
	}

	public MapConfig mapConfigCache() {
		
		return new MapConfig() //
				.setName("cache") //
				.setMaxSizeConfig(
						new MaxSizeConfig(MaxSizeConfig.DEFAULT_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
				.setEvictionPolicy(EvictionPolicy.LRU) //
				.setTimeToLiveSeconds(20);
	}
	
	public MapConfig mapConfigMonitor() {
		
		return new MapConfig() //
				.setName("transaction-monitor") //
				.setMaxSizeConfig(
						new MaxSizeConfig(MaxSizeConfig.DEFAULT_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
				.setEvictionPolicy(EvictionPolicy.LRU) //
				.setTimeToLiveSeconds(20);
	}

	public ExecutorConfig executorConfig() {
		
		return new ExecutorConfig("executor") //
				.setPoolSize(100) //
				.setQueueCapacity(Integer.MAX_VALUE);
	}
}
