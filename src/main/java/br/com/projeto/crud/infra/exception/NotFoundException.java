package br.com.projeto.crud.infra.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super("Objeto não enconrado.");
	}
}
