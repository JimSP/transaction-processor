package br.com.cafebinario.transactionprocessor.hazelcast.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cafebinario.transactionprocessor.hazelcast.data.TransactionData;

@Repository
public interface TransactionRepositories extends PagingAndSortingRepository<TransactionData, Long> {

	List<TransactionData> findByDateTimeBetween(@Param("from") final LocalDate from, @Param("to") final LocalDate to);

}
