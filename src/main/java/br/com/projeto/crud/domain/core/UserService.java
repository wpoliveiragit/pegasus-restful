package br.com.projeto.crud.domain.core;

import java.util.List;

import br.com.projeto.crud.domain.adapter.UserPersistenceAdapter;
import br.com.projeto.crud.domain.exception.ConflictException;
import br.com.projeto.crud.domain.exception.NotFoundException;
import br.com.projeto.crud.domain.model.UserModel;
import br.com.projeto.crud.domain.port.UserServicePort;
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
		return userPersistence.findById(login).orElseThrow(() -> new NotFoundException());
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
				.map(m -> {
					return userPersistence.update(model);
				}).orElseThrow(//
						() -> new NotFoundException());
	}

	@Override
	public void delete(String login) {
		userPersistence.findById(login).ifPresentOrElse(//
				model -> userPersistence.delete(model.getLogin()), //
				() -> new NotFoundException());
	}

}
