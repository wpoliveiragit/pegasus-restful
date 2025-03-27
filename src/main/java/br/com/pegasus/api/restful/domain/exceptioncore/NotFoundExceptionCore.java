package br.com.pegasus.api.restful.domain.exceptioncore;

public class NotFoundExceptionCore extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundExceptionCore() {
		super("Objeto não enconrado.");
	}
}
