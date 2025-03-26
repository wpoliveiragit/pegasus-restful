package br.com.projeto.crud.infra.provider.adapter;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.domain.adapter.UserPersistenceAdapter;
import br.com.projeto.crud.infra.config.mapper.UserMapper;
import br.com.projeto.crud.infra.model.UserModel;
import br.com.projeto.crud.infra.provider.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPersistenceAdapter {

	private final UserRepository userRepository;
	private final UserMapper mapper;

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<UserModel> findById(String id) {
		return userRepository.findById(id).map(mapper::toModel);
	}

	@Override
	public UserModel create(UserModel model) {
		return mapper.toModel(userRepository.save(mapper.toEntity(model)));
	}

	@Override
	public UserModel update(UserModel model) {
		return mapper.toModel(userRepository.save(mapper.toEntity(model)));
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

}
