package br.com.cafebinario.transactionprocessor.facades;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.predicates.FindByExamplePredicate;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.repositories.TransactionRepositories;

@Service
public class DefaultTransactionFacade implements TransactionService {

	@Autowired
	private TransactionRepositories transactionRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FindByExamplePredicate findByExamplePredicate;

	@Override
	public List<Transaction> getTransactions(final LocalDate from, final LocalDate to, final Transaction example) {

		return getStreamTransactions(from, to) //
				.get() //
				.map(transaction -> Pair.of((Object) example, (Object) transaction))
				.filter(pair -> findByExamplePredicate.test(pair)) //
				.map(Pair::getSecond) //
				.map(object -> (Transaction) object) //
				.collect(Collectors.toList());
	}

	@Override
	public List<Transaction> getTransactions(final LocalDate from, final LocalDate to) {

		return getStreamTransactions(from, to).get().collect(Collectors.toList());
	}

	@Override
	public Transaction create(final Transaction transaction) {

		return Transaction.builder().build();
	}

	@Override
	public Transaction cancel(final Transaction transaction) {

		return Transaction.builder().build();
	}

	@Override
	public Transaction reversal(final Transaction transaction) {

		return Transaction.builder().build();
	}

	@Override
	public Transaction chargeback(final Transaction transaction) {

		return Transaction.builder().build();
	}

	@Override
	public Transaction systemReversal(final Transaction transaction) {

		return Transaction.builder().build();
	}

	@Override
	public Transaction fraud(final Transaction transaction) {

		return Transaction.builder().build();
	}

	private Supplier<Stream<Transaction>> getStreamTransactions(final LocalDate from, final LocalDate to) {

		return () -> transactionRepositories //
				.findByDateTimeBetween(from, to) //
				.stream() //
				.map(transactionData -> modelMapper.map(transactionData, Transaction.class));
	}
}
