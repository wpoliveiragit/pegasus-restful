package br.com.pegasus.api.restful.domain.port;

import java.util.List;

import br.com.pegasus.api.restful.domain.model.UserModel;

public interface UserServicePort {

	List<UserModel> findAll();

	UserModel findById(String id);

	UserModel create(UserModel model);

	UserModel update(UserModel model);

	void delete(String id);

}
