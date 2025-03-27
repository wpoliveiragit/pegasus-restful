package br.com.pegasus.api.restful.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.pegasus.api.restful.app.dto.UserCreateBodyDTO;
import br.com.pegasus.api.restful.app.dto.UserUpdateBodyDTO;
import br.com.pegasus.api.restful.domain.model.UserModel;
import br.com.pegasus.api.restful.infra.provider.repository.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity toEntity(UserModel model);

	UserModel toModel(UserEntity entity);

	UserModel toModel(UserCreateBodyDTO dto);

	UserModel toModel(UserUpdateBodyDTO dto);

}
