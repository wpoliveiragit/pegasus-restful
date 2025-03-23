package br.com.projeto.crud.infra.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.crud.app.dto.ItemCreateBodyDto;
import br.com.projeto.crud.app.dto.ItemUpdateBodyDto;
import br.com.projeto.crud.domain.model.ItemModel;
import br.com.projeto.crud.infra.repository.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	ItemModel toModel(ItemCreateBodyDto request);

	ItemModel toModel(ItemUpdateBodyDto request);

	ItemEntity toEntity(ItemModel model);

	ItemModel toModel(ItemEntity entity);
}