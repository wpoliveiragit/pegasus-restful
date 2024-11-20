package br.com.projeto.crud.app.utils;

import org.springframework.http.HttpStatus;

import br.com.projeto.crud.app.controller.ItensController;
import br.com.projeto.crud.infra.utils.LoggerUtils;

public interface AdviceControllerConstantsUtils {

	LoggerUtils LOG = LoggerUtils.createLoggerSize30(ItensController.class);

	String MSG_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	String MSG_BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase();
	
	int STATIS_FAILURE = -1;

}
