package br.com.projeto.crud.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.domain.adapter.UserPersistenceAdapter;
import br.com.projeto.crud.domain.core.UserService;
import br.com.projeto.crud.domain.port.UserServicePort;
import br.com.projeto.crud.infra.repository.UserRepository;
import br.com.projeto.crud.infra.repository.adapter.UserRepositoryAdapter;

@Component
public class UserConfig {

	@Bean
	public UserServicePort createUserService(UserRepository userRepository) {
		UserPersistenceAdapter userPersistence = new UserRepositoryAdapter(userRepository);
		return new UserService(userPersistence);
	}

}
