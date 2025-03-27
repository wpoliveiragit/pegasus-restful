package br.com.pegasus.api.restful.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateBodyDTO {

	@JsonIgnore
	private Long id;
	private String name;

//	@Override
//	public String toString() {
//		return MethodUtil.convertToJsonMaskValues(this);
//	}

}