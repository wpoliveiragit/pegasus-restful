package br.com.projeto.crud.infra.config.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.crud.infra.factory.ResponseFactory;
import br.com.projeto.crud.infra.provider.UserRepository;
import br.com.projeto.crud.infra.provider.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MethodSecurity {

    /**
     * Caso não tenha altenticação ou se a credenciais forem invalidas.
     */

    public OncePerRequestFilter oncePerRequestFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                filterChain.doFilter(request, response);
            }
        };
    }

    /**
     * Caso não tenha altenticação ou se a credenciais forem invalidas.
     */
    public static AuthenticationEntryPoint unauthorizedAccessHandlerResponse() {
        ResponseFactory resp = new ResponseFactory();
        return (request, response, ex) -> {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(resp.securityUnauthorized().toString());
        };
    }

    /**
     * Filtra classes com @RequestMapping retorna o path do controller ou null
     * Remove nulos, formata path e retorna String[]
     */
    public static String[] getControllerPaths(Class<?>... controllerClazzes) {

        return Arrays.stream(controllerClazzes)//
                .filter(controllerClazze -> controllerClazze.isAnnotationPresent(RequestMapping.class))//
                .map(controllerClazze -> Arrays.stream(controllerClazze.getAnnotation(RequestMapping.class).value())//
                        .findFirst()//
                        .orElse(null))//
                .filter(Objects::nonNull)//
                .map(path -> "/".concat(path).concat("/**"))//
                .toArray(String[]::new);
    }

    public static UserDetails createUserDetails(String login, String password) {
        return User.builder()//
                .roles("RULES")//
                .username(login)//
                .password(password)//
                .build();
    }

    public static void addRootUser(UserRepository userPersistence, String login, String password) {
        UserEntity entity = new UserEntity();
        entity.setLogin(login);
        entity.setPasswaord(password);
        userPersistence.save(entity);
    }

}
