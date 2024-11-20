package br.com.projeto.crud.domain.model;

import java.io.Serializable;

import br.com.projeto.crud.infra.utils.SuportUtils;
import lombok.Getter;
import lombok.Setter;

public class ItensModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Getter @Setter int id;
	private @Getter @Setter String name;

	@Override
	public String toString() {
		return SuportUtils.GSON.toJson(this);
	}

	public ItensModel id(int id) {
		this.id = id;
		return this;
	}

	public ItensModel name(String name) {
		this.name = name;
		return this;
	}
}