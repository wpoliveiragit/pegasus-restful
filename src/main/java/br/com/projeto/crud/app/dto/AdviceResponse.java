package br.com.projeto.crud.app.dto;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import br.com.projeto.crud.infra.type.StatusType;
import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviceResponse {

	private String timestamp;
	private String message;
	private int code;

	private Object data;

	public AdviceResponse(StatusType status, Object data) {
		this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
		this.code = status.getCode();
		this.message = status.getMessage();
		this.data = data;
	}

	@Override
	public String toString() {
		return MethodUtil.convertToJsonMaskValues(this);
	}
}
