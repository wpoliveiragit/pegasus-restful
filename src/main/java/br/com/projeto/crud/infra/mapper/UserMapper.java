package br.com.projeto.crud.infra.mapper;

import br.com.projeto.crud.app.dto.UserCreateBodyDto;
import br.com.projeto.crud.app.dto.UserUpdateBodyDto;
import br.com.projeto.crud.domain.model.UserModel;
import br.com.projeto.crud.infra.repository.entity.UserEntity;

public interface UserMapper {

	UserMapper INSTANCE = new UserMapper() {
	};

	default UserEntity toEntity(UserModel model) {
		UserEntity entity = new UserEntity();
		entity.setLogin(model.getLogin());
		entity.setPasswaord(model.getPasswaord());
		return entity;
	}

	default UserModel toModel(UserEntity entity) {
		UserModel model = new UserModel();
		model.setLogin(entity.getLogin());
		model.setPasswaord(entity.getPasswaord());
		return model;
	}

	default UserModel toModel(UserCreateBodyDto body) {
		UserModel model = new UserModel();
		model.setLogin(body.getLogin());
		model.setPasswaord(body.getPasswaord());
		return model;
	}
	
	default UserModel toModel(UserUpdateBodyDto body) {
		UserModel model = new UserModel();
		model.setLogin(body.getLogin());
		model.setPasswaord(body.getPasswaord());
		return model;
	}

}
