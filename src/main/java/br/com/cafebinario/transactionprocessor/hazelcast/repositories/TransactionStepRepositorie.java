package br.com.cafebinario.transactionprocessor.hazelcast.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.cafebinario.transactionprocessor.hazelcast.data.TransactionStepData;

@Repository
public interface TransactionStepRepositorie extends PagingAndSortingRepository<TransactionStepData, Long> {

}
