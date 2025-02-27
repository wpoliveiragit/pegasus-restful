package br.com.projeto.crud.infra.config.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.crud.infra.factory.ResponseFactory;
import br.com.projeto.crud.infra.repository.UserRepository;
import br.com.projeto.crud.infra.repository.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import other.AppUtil;

public interface MethodSecurity {

	String ROLES = "USER";
	String MSG_ACCESS_DENIED = "Acesso não autorizado";
	String[] SWAGGER_PATHS = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" };
	String CONTENT_TYPE = "application/json; charset=UTF-8";

	/**
	 * Filtra classes com @RequestMapping retorna o path do controller ou null
	 * Remove nulos, formata path e retorna String[]
	 */
	default String[] getControllerPaths(Class<?>... controllerClazzes) {
		return Arrays.stream(controllerClazzes)//
				.filter(cc -> cc.isAnnotationPresent(RequestMapping.class))//
				.map(cc -> Arrays.stream(cc.getAnnotation(RequestMapping.class).value()).findFirst().orElse(null))//
				.filter(Objects::nonNull).map(path -> "/".concat(path).concat("/**")).toArray(String[]::new);
	}

	/**
	 * Busca os dados do login enviado por parametro, caso exista, criptografa o
	 * password obtido e gera um logon de verificação com o enviado.
	 */
	default UserDetailsService createUserDetailsService(BCryptPasswordEncoder crypt, UserRepository userPersistence) {
		return userName -> {
			try {
				UserEntity user = userPersistence.findById(userName)//
						.map(e -> e).orElseThrow(() -> new RuntimeException());
				return User.builder().roles(ROLES).username(userName).password(crypt.encode(user.getPasswaord()))
						.build();
			} catch (Exception ex) {
				return User.builder().roles(ROLES).username(userName).password("fail").build();
			}
		};
	}

	/** Caso não tenha altenticação ou se a credenciais forem invalidas. */
	default AuthenticationEntryPoint unauthorizedAccessHandlerResponse() {
		ResponseFactory resp = new ResponseFactory();
		return (request, response, ex) -> {
			response.setContentType(CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(AppUtil.GSON.toJson(resp.securityUnauthorized()));
		};
	}

	default OncePerRequestFilter oncePerRequestFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				filterChain.doFilter(request, response);
			}
		};
	}

}
