package br.com.pegasus.api.restful.domain.model;

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