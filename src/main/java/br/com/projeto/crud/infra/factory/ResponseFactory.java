package br.com.projeto.crud.infra.factory;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

public final class ResponseFactory {

	private static final int ITEM_BASE_CODE = 1000;
	private static final int USER_BASE_CODE = 2000;
	private static final int SECURITY_BASE_CODE = 3000;

	private static interface StatusBase {

		HttpStatus getHttpStatus();

		int getCode();

		String getMessage();
	}

	@RequiredArgsConstructor
	private enum SecurityStatus implements StatusBase {

		FORBIDDEN(HttpStatus.FORBIDDEN, SECURITY_BASE_CODE + 1, "Login sem privilégio."), //
		UNAUTHORIZED(HttpStatus.UNAUTHORIZED, SECURITY_BASE_CODE + 2, "Login/Senha Invalido."); //

		final HttpStatus httpStatus;
		final int code;
		final String message;

		@Override
		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		@Override
		public int getCode() {
			return code;
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	@RequiredArgsConstructor
	private enum ItemStatus implements StatusBase {

		FIND_ALL_NO_CONTENT(HttpStatus.NO_CONTENT, ITEM_BASE_CODE + 1, "Não a itens disponíveis."), //
		FIND_ALL(HttpStatus.OK, ITEM_BASE_CODE + 2, "Items encontrados com sucesso."), //
		FIND_BY_ID(HttpStatus.OK, ITEM_BASE_CODE + 3, "Item encontrado com sucesso."), //
		CREATE(HttpStatus.CREATED, ITEM_BASE_CODE + 4, "Item criado com sucesso."), //
		DELETE(HttpStatus.NO_CONTENT, ITEM_BASE_CODE + 5, "Item deletado com sucesso."), //
		UPDATE(HttpStatus.OK, ITEM_BASE_CODE + 6, "Item atualizado com sucesso.");

		final HttpStatus httpStatus;
		final int code;
		final String message;

		@Override
		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		@Override
		public int getCode() {
			return code;
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	@RequiredArgsConstructor
	private enum UserStatus implements StatusBase {

		FIND_ALL_NO_CONTENT(HttpStatus.NO_CONTENT, USER_BASE_CODE + 1, "Não a usuários disponíveis."), //
		FIND_ALL(HttpStatus.OK, USER_BASE_CODE + 2, "Usuários encontrados com sucesso."), //
		FIND_BY_ID(HttpStatus.OK, USER_BASE_CODE + 3, "Usuário encontrado com sucesso."), //
		CREATE(HttpStatus.CREATED, USER_BASE_CODE + 4, "Usuário criado com sucesso."), //
		DELETE(HttpStatus.NO_CONTENT, USER_BASE_CODE + 5, "Usuário deletado com sucesso."), //
		UPDATE(HttpStatus.OK, USER_BASE_CODE + 6, "Usuário atualizadado com sucesso.");

		final HttpStatus httpStatus;
		final int code;
		final String message;

		@Override
		public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		@Override
		public int getCode() {
			return code;
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	// CRIA O RESPONSE
	private ResponseEntity<?> createResponse(StatusBase status, Object data) {
		return ResponseEntity.status(status.getHttpStatus()).body(Map.of(//
				"timestamp", DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis())), //
				"code", status.getCode(), //
				"message", status.getMessage(), //
				"data", data));
	}

	// METODO ITEM
	public ResponseEntity<?> itemFindAllNoContent() {
		return createResponse(ItemStatus.FIND_ALL_NO_CONTENT, new ArrayList<Object>());
	}

	public ResponseEntity<?> itemFindAll(Object data) {
		return createResponse(ItemStatus.FIND_ALL, data);
	}

	public ResponseEntity<?> itemFindById(Object data) {
		return createResponse(ItemStatus.FIND_BY_ID, data);
	}

	public ResponseEntity<?> itemCreate(Object data) {
		return createResponse(ItemStatus.CREATE, data);
	}

	public ResponseEntity<?> itemUpdate(Object data) {
		return createResponse(ItemStatus.UPDATE, data);
	}

	public ResponseEntity<?> itemDelete() {
		return createResponse(ItemStatus.DELETE, new Object());
	}

	// METODO USER
	public ResponseEntity<?> userFindAllNoContent() {
		return createResponse(UserStatus.FIND_ALL_NO_CONTENT, new ArrayList<Object>());
	}

	public ResponseEntity<?> userFindAll(Object data) {
		return createResponse(UserStatus.FIND_ALL, data);
	}

	public ResponseEntity<?> userFindById(Object data) {
		return createResponse(UserStatus.FIND_BY_ID, data);
	}

	public ResponseEntity<?> userCreate(Object data) {
		return createResponse(UserStatus.CREATE, data);
	}

	public ResponseEntity<?> userUpdate(Object data) {
		return createResponse(UserStatus.UPDATE, data);
	}

	public ResponseEntity<?> userDelete() {
		return createResponse(UserStatus.DELETE, new Object());
	}

	// METODO SECURITY
	// credenciais invalidas ou não enviadas
	public ResponseEntity<?> securityForbidden() {
		return createResponse(SecurityStatus.FORBIDDEN, new Object());
	}

	// credenciais corretas porem não possui o nivel de acesso
	public ResponseEntity<?> securityUnauthorized() {
		return createResponse(SecurityStatus.UNAUTHORIZED, new Object());
	}

}
