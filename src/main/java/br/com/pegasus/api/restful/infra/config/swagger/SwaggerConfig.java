package br.com.pegasus.api.restful.infra.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@OpenAPIDefinition(//
		info = @Info(title = "API Documentation", version = "v1"), //
		security = { @SecurityRequirement(name = "basicAuth") })
public class SwaggerConfig {

	private static final String BASIC_AUTH = "basicAuth";
	private static final String BASIC = "basic";

	@Bean
	public OpenAPI customOpenAPI() {
		var secReq = new io.swagger.v3.oas.models.security.SecurityRequirement();

		return new OpenAPI().components(createComponents()).addSecurityItem(secReq.addList(BASIC_AUTH));
	}

	private Components createComponents() {
		Type http = io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;
		var securityScheme = new io.swagger.v3.oas.models.security.SecurityScheme();

		return new Components().addSecuritySchemes(BASIC_AUTH, securityScheme.type(http).scheme(BASIC));
	}

}
