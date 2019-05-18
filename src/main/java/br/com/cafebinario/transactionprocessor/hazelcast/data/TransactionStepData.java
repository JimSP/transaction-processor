package br.com.cafebinario.transactionprocessor.hazelcast.data;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.StepType;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@KeySpace("TransactionStepData")
public class TransactionStepData implements Serializable{

	private static final long serialVersionUID = 4046858745555329573L;

	@Id
	private Long identifier;
	
	private final Transaction transaction;
	private final StepType stepType;
}
