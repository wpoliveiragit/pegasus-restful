package br.com.projeto.crud.infra.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class MethodUtil {

	// MANTER ESTES OBJETOS INDIVIDUALIZADO
	private static final ObjectMapper CONVERT_TO_JSON_MASK_VALUES_MAPPER;
	private static final ObjectMapper CONVERT_TO_OBJECT_MAPPER;
	private static final ObjectMapper CONVERT_TO_JSON_MAPPER;

	static {
		// --
		CONVERT_TO_JSON_MASK_VALUES_MAPPER = new ObjectMapper();
		CONVERT_TO_JSON_MASK_VALUES_MAPPER.registerModule(new JavaTimeModule());

		// --
		CONVERT_TO_OBJECT_MAPPER = new ObjectMapper();
		CONVERT_TO_OBJECT_MAPPER.registerModule(new JavaTimeModule());

		// --
		CONVERT_TO_JSON_MAPPER = new ObjectMapper();
		CONVERT_TO_JSON_MAPPER.registerModule(new JavaTimeModule());
		CONVERT_TO_JSON_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

	}

	public static final String convertToJsonMaskValues(Object obj) {
		try {
			return CONVERT_TO_JSON_MASK_VALUES_MAPPER.writeValueAsString(obj);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "{Faul}";
		}
	}

	public static final <T> T convertToObject(String json, Class<T> t) throws Exception {
		return CONVERT_TO_OBJECT_MAPPER.readValue(json, t);
	}

	public static final String convertToJson(Object obj) throws Exception {
		return CONVERT_TO_JSON_MAPPER.writeValueAsString(obj);
	}

}
