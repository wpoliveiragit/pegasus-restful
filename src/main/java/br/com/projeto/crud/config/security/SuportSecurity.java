package br.com.projeto.crud.config.security;

import java.util.List;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import br.com.projeto.crud.domain.DTO.UnauthorizedAccessResponse;
import br.com.projeto.crud.infra.utils.LoggerUtils;
import br.com.projeto.crud.infra.utils.SuportUtils;
import jakarta.servlet.http.HttpServletResponse;

public interface SuportSecurity {

	LoggerUtils LOG = LoggerUtils.createLoggerSize30(SuportSecurity.class);
	String ROLES = "USER";
	String CONTENT_TYPE = "application/json; charset=UTF-8";

	default UserDetails createUserDetails(BCryptPasswordEncoder c, String u, String p) {
		return User.builder().roles(ROLES).username(u).password(c.encode(p)).build();
	}

	default Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> createsAccessConfigurationsRoutes(
			List<String> routes) {

		return authorizeRequests -> {
			authorizeRequests//
					// REMOVE SECURITY AUTHORIZATION DAS ROTAS
					.requestMatchers(routes.toArray(new String[0])).permitAll()//
					// ADD SECURITY AUTHORIZATION A TODAS AS ROTAS FORA DA LISTA 'routes'
					.anyRequest().authenticated();
			LOG.info("Removido 'Authorization' das rotas: {}", SuportUtils.GSON.toJson(routes));
		};
	}

	default AccessDeniedHandler accessDeniedHandlerResponse() {
		String message = "Acesso negado";
		return (request, response, ex) -> {
			UnauthorizedAccessResponse body = new UnauthorizedAccessResponse(ex, message);
			response.setContentType(CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write(body.toString());
			LOG.info(message + ": {}", body);
		};
	}

	default AuthenticationEntryPoint unauthorizedAccessHandlerResponse() {
		String message = "Acesso não autorizado";
		return (request, response, ex) -> {
			UnauthorizedAccessResponse body = new UnauthorizedAccessResponse(ex, message);
			response.setContentType(CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(body.toString());
			LOG.info(message + ": {}", body);
		};
	}

}
