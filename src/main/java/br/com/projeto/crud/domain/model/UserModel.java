package br.com.projeto.crud.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

@Entity
@Getter
@Setter
@Table(name = "TB_USER")
public class UserModel {

	@Id
	@NotBlank(message = "O login esta em branco!")
	private String login;

	@NotBlank(message = "O passwaord esta em branco!")
	private String passwaord;

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}

}
