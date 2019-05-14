package br.com.cafebinario.transactionprocessor.facade;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cafebinario.transactionprocessor.domain.terminals.models.Terminal;
import br.com.cafebinario.transactionprocessor.domain.terminals.services.TerminalService;

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
