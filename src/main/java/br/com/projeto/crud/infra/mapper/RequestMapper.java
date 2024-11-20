package br.com.projeto.crud.infra.mapper;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import br.com.projeto.crud.infra.utils.SuportUtils;

public interface RequestMapper {
	RequestMapper INSTANCE = new RequestMapper() {
	};

	default String createResponse(int status, String message, Object data) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("timestamp", DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis())));
		ret.put("status", status);
		ret.put("message", message);
		ret.put("data", data);

		return SuportUtils.GSON.toJson(ret);
	}

}
