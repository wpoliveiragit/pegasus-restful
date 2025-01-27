package br.com.projeto.crud.domain.model;

import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

public @Getter @Setter class ItemModel {

	private Long id;

	private String name;

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}

}