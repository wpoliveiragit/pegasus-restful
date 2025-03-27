package br.com.pegasus.api.restful.infra.config.security;

import br.com.pegasus.api.restful.app.controller.UserController;
import br.com.pegasus.api.restful.infra.provider.repository.UserRepository;
import br.com.pegasus.api.restful.infra.provider.repository.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return http
                .addFilterBefore(oncePerRequestFilter(), BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(getReleaseUrl(env)).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(super.unauthorizedAccessHandlerResponse()))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder crypt) {
        return userName -> {
            try {
                UserEntity user = userPersistence.findById(userName).orElseThrow(RuntimeException::new);
                return super.createUserDetails(userName, crypt.encode(user.getPasswaord()));
            } catch (Exception ex) {
                return super.createUserDetails(userName, "fail");
            }
        };
    }

    private String[] getReleaseUrl(Environment env) {
        List<String> releaseUrlList = new ArrayList<>();

        {//URL SWAGGER
            releaseUrlList.add("/v3/api-docs/**");
            releaseUrlList.add("/swagger-ui/**");
            releaseUrlList.add("/swagger-ui.html");
        }
        {//URL H2-CONSOLE
            String value = env.getProperty("spring.h2.console.path");
            if (value != null) {
                releaseUrlList.add(value.concat("/**"));
            }
        }
        {//ADD CONTROLLER
            releaseUrlList.addAll(Arrays.asList(super.getControllerPaths(UserController.class)));
        }
        return releaseUrlList.toArray(new String[0]);
    }
}
