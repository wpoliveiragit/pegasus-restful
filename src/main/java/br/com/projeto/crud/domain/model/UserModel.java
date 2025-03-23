package br.com.projeto.crud.domain.model;

import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

	private String login;

	private String passwaord;

	@Override
	public String toString() {
		return MethodUtil.convertToJsonMaskValues(this);
	}

}
