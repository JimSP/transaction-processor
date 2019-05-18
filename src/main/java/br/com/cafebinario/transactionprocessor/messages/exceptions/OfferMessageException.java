package br.com.cafebinario.transactionprocessor.messages.exceptions;

import java.util.function.Supplier;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class OfferMessageException extends RuntimeException {

	public static final long serialVersionUID = -1599194142269601638L;
	
	public static Supplier<OfferMessageException> of(final Exception ex){
		return () -> new OfferMessageException(ex);
	}

	
	private OfferMessageException(final Exception ex) {
		super(ex);
	}
}
