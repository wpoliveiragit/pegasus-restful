package br.com.projeto.crud.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateBodyDto {

	@JsonIgnore
	private Long id;
	private String name;

	@Override
	public String toString() {
		return MethodUtil.convertToJsonMaskValues(this);
	}

}