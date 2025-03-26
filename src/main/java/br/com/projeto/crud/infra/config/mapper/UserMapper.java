package br.com.projeto.crud.infra.config.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.crud.infra.dto.UserCreateBodyDTO;
import br.com.projeto.crud.infra.dto.UserUpdateBodyDTO;
import br.com.projeto.crud.infra.model.UserModel;
import br.com.projeto.crud.infra.provider.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity toEntity(UserModel model);

	UserModel toModel(UserEntity entity);

	UserModel toModel(UserCreateBodyDTO dto);

	UserModel toModel(UserUpdateBodyDTO dto);

}
