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
import br.com.projeto.crud.infra.provider.UserRepository;
import br.com.projeto.crud.infra.provider.entity.UserEntity;
import br.com.projeto.crud.infra.util.TXTUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CreateBeansSecurity extends MethodSecurity {

	private final UserRepository userPersistence;

	@PostConstruct
	public void init() {
		addRootUser(userPersistence, "root", "root");
	}

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain createSecurityFilterChain(HttpSecurity http, Environment env) throws Exception {
		String[] array = Stream.of(TXTUtil.SWAGGER_PATHS_ARRAY, super.getControllerPaths(UserController.class))//
				.flatMap(Arrays::stream)//
				.toArray(String[]::new);

		http.addFilterBefore(oncePerRequestFilter(), BasicAuthenticationFilter.class);

		SecurityFilterChain ret = http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())//
				.authorizeHttpRequests(// REMOVE AUTHORIZATION APENAS PARA AS ROTAS DA LISTA
						authorizeRequests -> authorizeRequests.requestMatchers(array).permitAll().anyRequest()
								.authenticated())
				.exceptionHandling(
						exHandling -> exHandling.authenticationEntryPoint(super.unauthorizedAccessHandlerResponse()))//
				.build();
		return ret;
	}

	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder crypt) {
		/**
		 * Busca os dados do login enviado por parametro, caso exista, criptografa o
		 * password obtido e gera um logon de verificação com o enviado.
		 */
		return userName -> {
			try {
				UserEntity user = userPersistence.findById(userName).map(e -> e).orElseThrow(RuntimeException::new);
				return super.createUserDetails(userName, crypt.encode(user.getPasswaord()));
			} catch (Exception ex) {
				return super.createUserDetails(userName, "fail");
			}
		};
	}

}
