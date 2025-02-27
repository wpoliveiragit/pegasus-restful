package br.com.projeto.crud.infra.config.security;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.projeto.crud.app.controller.UserController;
import br.com.projeto.crud.infra.repository.UserRepository;
import br.com.projeto.crud.infra.repository.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CreateBeansSecurity implements MethodSecurity {

	private final UserRepository userPersistence;

	@PostConstruct
	public void loadUserData() {
		UserEntity entity = new UserEntity();
		entity.setLogin("root");
		entity.setPasswaord("root");
		userPersistence.save(entity);
	}

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain createSecurityFilterChain(HttpSecurity http, Environment env) throws Exception {
		String[] array = Stream.of(SWAGGER_PATHS, getControllerPaths(UserController.class))//
				.flatMap(Arrays::stream).toArray(String[]::new);

		http.addFilterBefore(oncePerRequestFilter(), BasicAuthenticationFilter.class);

		SecurityFilterChain ret = http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())//
				.authorizeHttpRequests(authorizeRequests -> {// REMOVE AUTHORIZATION APENAS PARA AS ROTAS DA LISTA
					authorizeRequests.requestMatchers(array).permitAll().anyRequest().authenticated();
				})
				.exceptionHandling(
						exHandling -> exHandling.authenticationEntryPoint(unauthorizedAccessHandlerResponse()))//
				.build();
		return ret;
	}

	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder crypt) {
		return createUserDetailsService(crypt, userPersistence);
	}

}
