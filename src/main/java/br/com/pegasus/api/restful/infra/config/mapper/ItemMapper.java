package br.com.pegasus.api.restful.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.pegasus.api.restful.app.dto.ItemCreateBodyDTO;
import br.com.pegasus.api.restful.app.dto.ItemUpdateBodyDTO;
import br.com.pegasus.api.restful.domain.model.ItemModel;
import br.com.pegasus.api.restful.infra.provider.repository.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	ItemEntity toEntity(ItemModel model);

	ItemModel toModel(ItemEntity entity);

	ItemModel toModel(ItemCreateBodyDTO dto);

	ItemModel toModel(ItemUpdateBodyDTO dto);

}