package br.com.cafebinario.transactionprocessor.domain.terminals.services;

import java.util.List;

import br.com.cafebinario.transactionprocessor.domain.terminals.models.Terminal;

public interface TerminalService {
	
	List<Terminal> findBy(final Terminal terminalExample);
	Terminal create(final Terminal terminal);
	Terminal exclude(final Terminal terminal);
	Terminal update(final Terminal terminal);

}
