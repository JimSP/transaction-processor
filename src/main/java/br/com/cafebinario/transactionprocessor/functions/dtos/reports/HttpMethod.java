package br.com.cafebinario.transactionprocessor.functions.dtos.reports;

public enum HttpMethod {

	POST {
		@Override
		public String getPath(final String name) {
			return String.format("/%s", name);
		}
	}, GET {
		@Override
		public String getPath(final String name) {
			return String.format("/%s/{item}", name);
		}
	};
	
	public abstract String getPath(final String name);
}
