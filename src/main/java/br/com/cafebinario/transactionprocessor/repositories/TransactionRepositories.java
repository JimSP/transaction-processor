package br.com.cafebinario.transactionprocessor.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepositories extends PagingAndSortingRepository<TransactionData, Long> {

	List<TransactionData> findByDateTimeBetween(@Param("from") final LocalDate from, @Param("to") final LocalDate to);

}
