package br.com.pegasus.api.restful.infra.type;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusType {
	INTER_SERV_ERR(-1, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), //
	CONFLICT(-2, HttpStatus.CONFLICT.getReasonPhrase()), //
	NOT_FOUND(-3, HttpStatus.NOT_FOUND.getReasonPhrase());

	private final int code;
	private final String message;
}
