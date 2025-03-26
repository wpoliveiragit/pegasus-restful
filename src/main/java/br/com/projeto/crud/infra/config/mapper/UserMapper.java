package br.com.projeto.crud.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.crud.infra.dto.UserCreateBodyDto;
import br.com.projeto.crud.infra.dto.UserUpdateBodyDto;
import br.com.projeto.crud.infra.model.UserModel;
import br.com.projeto.crud.infra.provider.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity toEntity(UserModel model);

	UserModel toModel(UserEntity entity);

	UserModel toModel(UserCreateBodyDto dto);

	UserModel toModel(UserUpdateBodyDto dto);

}
