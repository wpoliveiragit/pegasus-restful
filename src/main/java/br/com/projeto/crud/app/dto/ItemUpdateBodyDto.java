package br.com.projeto.crud.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

public @Getter @Setter class ItemUpdateBodyDto {

	@JsonIgnore
	private Long id;

	private String name;

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}

}