package br.com.projeto.crud.app.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.projeto.crud.infra.dto.ResponseAdviceDTO;
import br.com.projeto.crud.infra.exception.ConflictException;
import br.com.projeto.crud.infra.exception.NotFoundException;
import br.com.projeto.crud.infra.type.StatusType;

@ControllerAdvice
public class ControllerAdviceConfig {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ResponseAdviceDTO> handleRuntimeException(IllegalStateException ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.INTER_SERV_ERR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseAdviceDTO> handleException(Exception ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.INTER_SERV_ERR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ResponseAdviceDTO> handleNullPointerException(ConflictException ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.CONFLICT, ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseAdviceDTO> handleNullPointerException(NotFoundException ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
