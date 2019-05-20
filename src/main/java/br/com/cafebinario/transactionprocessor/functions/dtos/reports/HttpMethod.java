package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

public enum HttpMethod {

	POST, GET;
	
	public String getPath(final String name) {
		return String.format("/%s", name);
	}
}
