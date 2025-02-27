package br.com.projeto.crud.app.dto;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

public @Getter @Setter class AppResponseDto {

	private String timestamp;
	private String message;
	private int code;

	private Object data;

	public AppResponseDto(int code, String message, Object data) {
		this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}
}
