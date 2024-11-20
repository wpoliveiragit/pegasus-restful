package br.com.projeto.crud.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.crud.domain.model.UserModel;
import br.com.projeto.crud.infra.annotation.LogBean;
import br.com.projeto.crud.infra.dao.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CreateBeansSecurity implements SuportSecurity {

	private interface Const {
		String MSG_PASSWORD_ENCODER = "Tipo de criptografia para senha de usuário criado";
		String MSG_SECURITY_FILTER_CHAIN = "Cadeia de Filtros de Segurança criada";
		String MSG_USER_DETAILS_SERVICE = "Verificação da autenticassão de usuário criada.";
	}

	private UserDao userDao;

	public CreateBeansSecurity(UserDao userDao) {
		this.userDao = userDao;
	}

	@Bean
	@LogBean(message = Const.MSG_PASSWORD_ENCODER)
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@LogBean(message = Const.MSG_SECURITY_FILTER_CHAIN)
	public SecurityFilterChain createSecurityFilterChain(HttpSecurity http, Environment env) throws Exception {
		List<String> routes = new ArrayList<>();
		routes.addAll(Arrays.asList(env.getProperty("app.security.url.swagger").split(",")));
		routes.add("/" + env.getProperty("app.controller.user.path") + "/**");

		OncePerRequestFilter oncePerRequestFilter = new OncePerRequestFilter() {

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {

				try {
					// Prossegue com o filtro
					filterChain.doFilter(request, response);
				} catch (AuthenticationException ex) {
					// Captura exceção específica de BadCredentialsException
					if (ex instanceof BadCredentialsException) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário ou senha inválidos.");
					} else {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falha de autenticação.");
					}
				}

			}
		};

		http.addFilterBefore(oncePerRequestFilter, BasicAuthenticationFilter.class);

		SecurityFilterChain ret = http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())//
				.authorizeHttpRequests(createsAccessConfigurationsRoutes(routes))//
				.exceptionHandling(exceptionHandling -> exceptionHandling//
						.authenticationEntryPoint(unauthorizedAccessHandlerResponse())//
						.accessDeniedHandler(accessDeniedHandlerResponse()))//
				.build();
		return ret;
	}

	@Bean
	@LogBean(message = Const.MSG_USER_DETAILS_SERVICE)
	public UserDetailsService userDetailsService(BCryptPasswordEncoder crypt) {
		return checkUser -> {
			try {
				Optional<UserModel> opt = userDao.findById(checkUser);
				return opt.isPresent() //
						? createUserDetails(crypt, checkUser, opt.get().getPasswaord()) //
						: createUserDetails(crypt, "fail", "fail");
			} catch (Exception e) {
				return createUserDetails(crypt, "fail", "fail");
			}
		};
	}

}
