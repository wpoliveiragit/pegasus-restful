package br.com.projeto.crud.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.crud.infra.dto.ItemCreateBodyDto;
import br.com.projeto.crud.infra.dto.ItemUpdateBodyDto;
import br.com.projeto.crud.infra.model.ItemModel;
import br.com.projeto.crud.infra.provider.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	ItemEntity toEntity(ItemModel model);

	ItemModel toModel(ItemEntity entity);

	ItemModel toModel(ItemCreateBodyDto dto);

	ItemModel toModel(ItemUpdateBodyDto dto);

}