package br.com.pegasus.api.restful.domain.core;

import java.util.List;

import br.com.pegasus.api.restful.domain.adapter.UserPersistenceAdapter;
import br.com.pegasus.api.restful.domain.port.UserServicePort;
import br.com.pegasus.api.restful.domain.exceptioncore.ConflictExceptionCore;
import br.com.pegasus.api.restful.domain.exceptioncore.NotFoundExceptionCore;
import br.com.pegasus.api.restful.domain.model.UserModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService implements UserServicePort {

	private final UserPersistenceAdapter userPersistence;

	@Override
	public List<UserModel> findAll() {
		return userPersistence.findAll();
	}

	@Override
	public UserModel findById(String login) {
		return userPersistence.findById(login)//
				.orElseThrow(() -> new NotFoundExceptionCore());
	}

	@Override
	public UserModel create(UserModel model) {
		userPersistence.findById(model.getLogin()).ifPresent(e -> {
			throw new ConflictExceptionCore();
		});
		return userPersistence.create(model);
	}

	@Override
	public UserModel update(UserModel model) {
		return userPersistence.findById(model.getLogin())//
				.map(m -> userPersistence.update(model)).orElseThrow(NotFoundExceptionCore::new);
	}

	@Override
	public void delete(String login) {
		userPersistence.findById(login)//
				.ifPresentOrElse(model -> userPersistence.delete(model.getLogin()), NotFoundExceptionCore::new);
	}

}
