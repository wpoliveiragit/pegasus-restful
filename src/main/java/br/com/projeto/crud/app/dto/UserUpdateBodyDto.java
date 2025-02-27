package br.com.projeto.crud.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

public @Getter @Setter class UserUpdateBodyDto {

	@JsonIgnore
	private String login;

	private String passwaord;

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}

}
