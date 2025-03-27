package br.com.pegasus.api.restful.domain.adapter;

import java.util.List;
import java.util.Optional;

import br.com.pegasus.api.restful.domain.model.UserModel;

public interface UserPersistenceAdapter {

	List<UserModel> findAll();

	Optional<UserModel> findById(String id);

	UserModel create(UserModel model);

	UserModel update(UserModel model);

	void delete(String id);

}
