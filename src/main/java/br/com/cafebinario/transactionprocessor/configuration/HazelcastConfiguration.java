package br.com.cafebinario.transactionprocessor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configurable
@EnableHazelcastRepositories
@EnableCaching
public class HazelcastConfiguration {

	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance hazelcastInstance(@Autowired final Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public Config config(@Autowired final MapConfig mapConfig) {
		return new Config("transaction-processor") //
				.addMapConfig(mapConfig);
	}

	@Bean
	public MapConfig mapConfig() {
		return new MapConfig() //
				.setName("instruments") //
				.setMaxSizeConfig(
						new MaxSizeConfig(MaxSizeConfig.DEFAULT_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)) //
				.setEvictionPolicy(EvictionPolicy.LRU) //
				.setTimeToLiveSeconds(20);
	}
}