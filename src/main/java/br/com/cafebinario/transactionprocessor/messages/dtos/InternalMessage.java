package br.com.cafebinario.transactionprocessor.messages.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternalMessage<T extends Serializable> {

	private final T contentData;
	private final String urlCallback;
	private final LocalDateTime createdDateTime;
	private final Route route;
}
