package br.com.projeto.crud.domain.DTO;

import java.io.Serializable;

import br.com.projeto.crud.infra.utils.SuportUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String passwaord;

	public UserRequestDTO login(String login) {
		this.login = login;
		return this;
	}

	public UserRequestDTO passwaord(String passwaord) {
		this.passwaord = passwaord;
		return this;
	}

	@Override
	public String toString() {
		return SuportUtils.GSON.toJson(this);
	}

}
