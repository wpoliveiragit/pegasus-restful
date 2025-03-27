package br.com.pegasus.api.restful.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateBodyDTO {

	private String login;
	@JsonIgnore
	private String passwaord;

//	@Override
//	public String toString() {
//		return MethodUtil.convertToJsonMaskValues(this);
//	}

}
