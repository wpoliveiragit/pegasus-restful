package br.com.projeto.crud.app.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.crud.infra.dto.AdviceResponse;
import br.com.projeto.crud.infra.exception.ConflictException;
import br.com.projeto.crud.infra.exception.NotFoundException;
import br.com.projeto.crud.infra.type.StatusType;

@ControllerAdvice
public class ControllerAdviceConfig {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<AdviceResponse> handleRuntimeException(IllegalStateException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AdviceResponse response = new AdviceResponse(StatusType.INTER_SERV_ERR, model);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AdviceResponse> handleException(Exception ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AdviceResponse response = new AdviceResponse(StatusType.INTER_SERV_ERR, model);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<AdviceResponse> handleNullPointerException(ConflictException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AdviceResponse response = new AdviceResponse(StatusType.CONFLICT, model);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<AdviceResponse> handleNullPointerException(NotFoundException ex) {

		Map<String, Object> model = new HashMap<>(1);
		model.put("description", ex.getMessage());
		AdviceResponse response = new AdviceResponse(StatusType.NOT_FOUND, model);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
