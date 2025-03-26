package br.com.projeto.crud.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateBodyDto {

	@JsonIgnore
	private String login;
	private String passwaord;

	@Override
	public String toString() {
		return MethodUtil.convertToJsonMaskValues(this);
	}

}
