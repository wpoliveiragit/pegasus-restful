package br.com.projeto.crud.domain.core;

import java.util.List;

import br.com.projeto.crud.domain.adapter.UserPersistenceAdapter;
import br.com.projeto.crud.domain.port.UserServicePort;
import br.com.projeto.crud.infra.exception.ConflictException;
import br.com.projeto.crud.infra.exception.NotFoundException;
import br.com.projeto.crud.infra.model.UserModel;
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
				.orElseThrow(() -> new NotFoundException());
	}

	@Override
	public UserModel create(UserModel model) {
		userPersistence.findById(model.getLogin()).ifPresent(e -> {
			throw new ConflictException();
		});
		return userPersistence.create(model);
	}

	@Override
	public UserModel update(UserModel model) {
		return userPersistence.findById(model.getLogin())//
				.map(m -> userPersistence.update(model)).orElseThrow(NotFoundException::new);
	}

	@Override
	public void delete(String login) {
		userPersistence.findById(login)//
				.ifPresentOrElse(model -> userPersistence.delete(model.getLogin()), NotFoundException::new);
	}

}
