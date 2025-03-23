package br.com.projeto.crud.domain.DTO;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedAccessResponse {

	private String timestamp;
	private String message; // acesso negado ou acesso não permitido
	private String description;// exception.getmessage

	public UnauthorizedAccessResponse(Exception ex, String message) {
		this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
		this.description = ex.getMessage();
		this.message = message;

	}

	@Override
	public String toString() {
		return MethodUtil.convertToJsonMaskValues(this);
	}
}
