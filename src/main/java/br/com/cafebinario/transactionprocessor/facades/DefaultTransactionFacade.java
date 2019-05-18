package br.com.cafebinario.transactionprocessor.facades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.monitor.services.MonitorService;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Reason;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.StepType;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.TransactionStep;
import br.com.cafebinario.transactionprocessor.domains.transactions.predicates.FindByExamplePredicate;
import br.com.cafebinario.transactionprocessor.domains.transactions.services.TransactionService;
import br.com.cafebinario.transactionprocessor.hazelcast.data.TransactionData;
import br.com.cafebinario.transactionprocessor.hazelcast.data.TransactionStepData;
import br.com.cafebinario.transactionprocessor.hazelcast.repositories.TransactionRepositories;
import br.com.cafebinario.transactionprocessor.hazelcast.repositories.TransactionStepRepositorie;

@Service
public class DefaultTransactionFacade implements TransactionService {

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private TransactionRepositories transactionRepositories;

	@Autowired
	private TransactionStepRepositorie transactionStepRepositorie;

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
	public Transaction getTransaction(final Long identifier) {
		return modelMapper //
				.map(transactionRepositories.findById(identifier), Transaction.TransactionBuilder.class) //
				.build();
	}

	@Override
	public Transaction create(final Transaction transaction) {

		final Transaction transactionSaved = modelMapper //
				.map(transactionRepositories //
						.save( //
								modelMapper //
										.map(transaction, TransactionData.TransactionDataBuilder.class) //
										.dateTime(LocalDateTime.now()) //
										.reason(Reason.ACCEPT) //
										.build()), //
						Transaction.TransactionBuilder.class) //
				.build();

		transactionStepRepositorie //
				.save(TransactionStepData //
						.builder() //
						.identifier(transactionSaved.getIdentifier()) //
						.stepType(StepType.CREATE) //
						.transaction(transactionSaved) //
						.build());

		return transactionSaved;
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

	@Override
	public void monitoring(final Transaction transaction) {

		monitorService //
				.register( //
						TransactionStep //
								.builder() //
								.stepType(StepType.CREATE) //
								.transaction(transaction) //
								.build(), //
						TransactionStep.class);
	}

	private Supplier<Stream<Transaction>> getStreamTransactions(final LocalDate from, final LocalDate to) {

		return () -> transactionRepositories //
				.findByDateTimeBetween(from, to) //
				.stream() //
				.map(transactionData -> modelMapper //
						.map(transactionData, //
								Transaction.TransactionBuilder.class) //
						.build());
	}
}
