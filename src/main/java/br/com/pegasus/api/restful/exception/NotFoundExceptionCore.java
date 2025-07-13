package br.com.pegasus.api.restful.exception;

public class NotFoundExceptionCore extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundExceptionCore() {
		super("Objeto não enconrado.");
	}
}
