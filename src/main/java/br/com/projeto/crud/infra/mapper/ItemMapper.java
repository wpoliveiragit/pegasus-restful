package br.com.projeto.crud.infra.mapper;

import br.com.projeto.crud.app.dto.ItemCreateBodyDto;
import br.com.projeto.crud.app.dto.ItemUpdateBodyDto;
import br.com.projeto.crud.domain.model.ItemModel;
import br.com.projeto.crud.infra.repository.entity.ItemEntity;

public interface ItemMapper {

	ItemMapper INSTANCE = new ItemMapper() {
	};

	default ItemEntity toEntity(ItemModel model) {
		ItemEntity entity = new ItemEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		return entity;
	}

	default ItemModel toModel(ItemEntity entity) {
		ItemModel model = new ItemModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		return model;
	}
	
	default ItemModel toModel(ItemCreateBodyDto request) {
		ItemModel model = new ItemModel();
		model.setId(request.getId());
		model.setName(request.getName());
		return model;
	}
	
	default ItemModel toModel(ItemUpdateBodyDto request) {
		ItemModel model = new ItemModel();
		model.setId(request.getId());
		model.setName(request.getName());
		return model;
	}

}
