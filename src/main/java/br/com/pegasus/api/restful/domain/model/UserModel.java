package br.com.pegasus.api.restful.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

	private String login;
	private String passwaord;

//	@Override
//	public String toString() {
//		return MethodUtil.convertToJsonMaskValues(this);
//	}

}
