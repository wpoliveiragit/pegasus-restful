package br.com.projeto.crud.infra.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.crud.app.dto.AppResponseDto;
import br.com.projeto.crud.domain.exception.ConflictException;
import br.com.projeto.crud.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
public class ControllerAdviceConfig {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<AppResponseDto> handleRuntimeException(IllegalStateException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AppResponseDto response = new AppResponseDto(Status.INTER_SERV_ERR.code, Status.INTER_SERV_ERR.message, model);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AppResponseDto> handleException(Exception ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AppResponseDto response = new AppResponseDto(Status.INTER_SERV_ERR.code, Status.INTER_SERV_ERR.message, model);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<AppResponseDto> handleNullPointerException(ConflictException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AppResponseDto response = new AppResponseDto(Status.CONFLICT.code, Status.CONFLICT.message, model);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<AppResponseDto> handleNullPointerException(NotFoundException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AppResponseDto response = new AppResponseDto(Status.NOT_FOUND.code, Status.NOT_FOUND.message, model);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@RequiredArgsConstructor
	enum Status {
		INTER_SERV_ERR(-1, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), //
		CONFLICT(-2, HttpStatus.CONFLICT.getReasonPhrase()), //
		NOT_FOUND(-3, HttpStatus.NOT_FOUND.getReasonPhrase());

		public final int code;
		public final String message;
	}

}
