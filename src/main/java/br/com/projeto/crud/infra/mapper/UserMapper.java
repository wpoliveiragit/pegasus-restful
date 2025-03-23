package br.com.projeto.crud.infra.mapper;

import br.com.projeto.crud.app.dto.UserCreateBodyDto;
import br.com.projeto.crud.app.dto.UserUpdateBodyDto;
import br.com.projeto.crud.domain.model.UserModel;
import br.com.projeto.crud.infra.repository.entity.UserEntity;

public final class UserMapper {

	private static UserMapper INSTANCE;

	private UserMapper() {
	}

	public static UserMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserMapper();
		}
		return INSTANCE;
	}

	public UserEntity toEntity(UserModel model) {
		UserEntity entity = new UserEntity();
		entity.setLogin(model.getLogin());
		entity.setPasswaord(model.getPasswaord());
		return entity;
	}

	public UserModel toModel(UserEntity entity) {
		UserModel model = new UserModel();
		model.setLogin(entity.getLogin());
		model.setPasswaord(entity.getPasswaord());
		return model;
	}

	public UserModel toModel(UserCreateBodyDto body) {
		UserModel model = new UserModel();
		model.setLogin(body.getLogin());
		model.setPasswaord(body.getPasswaord());
		return model;
	}

	public UserModel toModel(UserUpdateBodyDto body) {
		UserModel model = new UserModel();
		model.setLogin(body.getLogin());
		model.setPasswaord(body.getPasswaord());
		return model;
	}

}
