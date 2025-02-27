package br.com.projeto.crud.infra.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

@Entity
@Table(name = "TB_USER")
public @Getter @Setter class UserEntity {

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
