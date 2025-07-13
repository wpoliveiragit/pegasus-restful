package br.com.pegasus.api.restful.exception;

public class ConflictExceptionCore extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflictExceptionCore() {
		super("Já existe um registro deste objeto.");
	}
}
