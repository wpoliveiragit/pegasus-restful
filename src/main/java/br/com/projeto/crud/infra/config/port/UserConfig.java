package br.com.projeto.crud.infra.config.port;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.domain.adapter.UserPersistenceAdapter;
import br.com.projeto.crud.domain.core.UserService;
import br.com.projeto.crud.domain.port.UserServicePort;
import br.com.projeto.crud.infra.config.mapper.UserMapper;
import br.com.projeto.crud.infra.provider.UserRepository;
import br.com.projeto.crud.infra.provider.adapter.UserRepositoryAdapter;

@Component
public class UserConfig {

	@Bean
	public UserServicePort createUserService(UserRepository userRepository, UserMapper userMapper) {
		UserPersistenceAdapter userPersistence = new UserRepositoryAdapter(userRepository, userMapper);
		return new UserService(userPersistence);
	}

}
