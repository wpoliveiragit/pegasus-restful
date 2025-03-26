package br.com.projeto.crud.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.crud.infra.dto.ItemCreateBodyDTO;
import br.com.projeto.crud.infra.dto.ItemUpdateBodyDTO;
import br.com.projeto.crud.infra.model.ItemModel;
import br.com.projeto.crud.infra.provider.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	ItemEntity toEntity(ItemModel model);

	ItemModel toModel(ItemEntity entity);

	ItemModel toModel(ItemCreateBodyDTO dto);

	ItemModel toModel(ItemUpdateBodyDTO dto);

}