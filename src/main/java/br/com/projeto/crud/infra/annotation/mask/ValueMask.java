package br.com.projeto.crud.infra.annotation.mask;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.projeto.crud.infra.annotation.mask.ValueMask.ObjectJsonSerializerMask;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = ObjectJsonSerializerMask.class)
public @interface ValueMask {// add no atributo que deseja omitir o dado

	public static class ObjectJsonSerializerMask extends JsonSerializer<Object> {

		@Override
		public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString("**OMITTED**");
		}

	}

}
