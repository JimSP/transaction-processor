package br.com.cafebinario.transactionprocessor.facades;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domains.terminals.models.Terminal;
import br.com.cafebinario.transactionprocessor.domains.terminals.services.TerminalService;

@Service
public class DefaultTerminalFacade implements TerminalService{

	@Override
	public List<Terminal> findBy(Terminal terminalExample) {
		return Collections.emptyList();
	}

	@Override
	public Terminal create(Terminal terminal) {
		return Terminal.builder().build();
	}

	@Override
	public Terminal exclude(Terminal terminal) {
		return Terminal.builder().build();
	}

	@Override
	public Terminal update(Terminal terminal) {
		return Terminal.builder().build();
	}

}
