package br.com.projeto.crud.domain.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import br.com.projeto.crud.infra.utils.SuportUtils;
import lombok.Getter;
import lombok.Setter;

public class ResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Getter @Setter String timestamp;
	private @Getter @Setter int status;
	private @Getter @Setter String message;
	private @Getter @Setter Object data;

	public ResponseDTO(int status, String message, Object data) {
		this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return SuportUtils.GSON.toJson(this);
	}
}
