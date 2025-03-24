package br.com.projeto.crud.infra.exception;

public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflictException() {
		super("Já existe um registro deste objeto.");
	}
}
