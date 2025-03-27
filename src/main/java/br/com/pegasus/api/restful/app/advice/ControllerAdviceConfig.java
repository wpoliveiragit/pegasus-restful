package br.com.pegasus.api.restful.app.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pegasus.api.restful.app.dto.ResponseAdviceDTO;
import br.com.pegasus.api.restful.domain.exceptioncore.ConflictExceptionCore;
import br.com.pegasus.api.restful.domain.exceptioncore.NotFoundExceptionCore;
import br.com.pegasus.api.restful.infra.type.StatusType;

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

	@ExceptionHandler(ConflictExceptionCore.class)
	public ResponseEntity<ResponseAdviceDTO> handleNullPointerException(ConflictExceptionCore ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.CONFLICT, ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(NotFoundExceptionCore.class)
	public ResponseEntity<ResponseAdviceDTO> handleNullPointerException(NotFoundExceptionCore ex) {

		ResponseAdviceDTO response = new ResponseAdviceDTO(StatusType.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
