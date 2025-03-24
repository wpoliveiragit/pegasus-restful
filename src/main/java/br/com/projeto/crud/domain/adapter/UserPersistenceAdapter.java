package br.com.projeto.crud.domain.adapter;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.infra.model.UserModel;

public interface UserPersistenceAdapter {

	List<UserModel> findAll();

	Optional<UserModel> findById(String id);

	UserModel create(UserModel model);

	UserModel update(UserModel model);

	void delete(String id);

}
