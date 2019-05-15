package br.com.cafebinario.transactionprocessor.repositories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import br.com.cafebinario.transactionprocessor.domains.transactions.models.Reason;
import br.com.cafebinario.transactionprocessor.domains.transactions.models.WayPayment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@KeySpace("TransactionData")
public class TransactionData implements Comparable<TransactionData>, Serializable{

	private static final long serialVersionUID = -263021921861770826L;

	@Id
	private Long id;
	
	private BigDecimal value;
	private LocalDateTime dateTime;
	private WayPayment wayPayment;
	private String cardIdentifier;
	private Long terminalIdentifier;
	private Long storeIdentifier;
	private Long cardHolderIdentifier;
	private Long origialTransactionId;
	private Reason reason;
	
	@Override
	public int compareTo(final TransactionData otherTransactionData) {
		return Long.compare(id, otherTransactionData.id);
	}

}
