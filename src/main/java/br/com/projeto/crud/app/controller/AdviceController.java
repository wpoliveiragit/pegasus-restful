package br.com.projeto.crud.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.projeto.crud.app.utils.AdviceControllerConstantsUtils;
import br.com.projeto.crud.domain.DTO.ResponseDTO;
import br.com.projeto.crud.infra.mapper.createResponseDTO;

@ControllerAdvice
public class AdviceController implements AdviceControllerConstantsUtils {

	public AdviceController() {
		LOG.infoCreateBean();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {

		ResponseDTO responseDTO = new ResponseDTO(STATIS_FAILURE, MSG_INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
		
		
		ResponseDTO body = createResponseDTO.fail(ex.getLocalizedMessage(), MSG_INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponseDTO> handleNullPointerException(NullPointerException ex) {

		ResponseDTO body = createResponseDTO.fail(ex.getLocalizedMessage(), MSG_BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {

		ResponseDTO body = createResponseDTO.fail(
				ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList(),
				"Problema em {" + ex.getBindingResult().getObjectName() + "}");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
		return new ResponseEntity<>("Usuário ou senha inválidos", HttpStatus.UNAUTHORIZED);
	}

}
