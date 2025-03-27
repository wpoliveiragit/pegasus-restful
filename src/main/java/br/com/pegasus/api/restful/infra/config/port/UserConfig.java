package br.com.pegasus.api.restful.infra.config.port;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.pegasus.api.restful.domain.adapter.UserPersistenceAdapter;
import br.com.pegasus.api.restful.domain.core.UserService;
import br.com.pegasus.api.restful.domain.port.UserServicePort;
import br.com.pegasus.api.restful.infra.config.mapper.UserMapper;
import br.com.pegasus.api.restful.infra.provider.repository.UserRepository;
import br.com.pegasus.api.restful.infra.provider.adapter.UserRepositoryAdapter;

@Component
public class UserConfig {

	@Bean
	public UserServicePort createUserService(UserRepository userRepository, UserMapper userMapper) {
		UserPersistenceAdapter userPersistence = new UserRepositoryAdapter(userRepository, userMapper);
		return new UserService(userPersistence);
	}

}
