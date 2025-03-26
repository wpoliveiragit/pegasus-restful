package br.com.projeto.crud.infra.model;

import br.com.projeto.crud.infra.util.MethodUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemModel {

	private Long id;
	private String name;

//	@Override
//	public String toString() {
//		return MethodUtil.convertToJsonMaskValues(this);
//	}

}