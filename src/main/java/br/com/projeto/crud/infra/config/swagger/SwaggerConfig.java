package br.com.projeto.crud.infra.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
@OpenAPIDefinition(//
		info = @Info(title = "API Documentation", version = "v1"), //
		security = { @SecurityRequirement(name = "basicAuth") })
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		OpenAPI openApi = new OpenAPI().components(//
				new Components().addSecuritySchemes(//
						"basicAuth",
						new io.swagger.v3.oas.models.security.SecurityScheme()
								.type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)//
								.scheme("basic")))
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("basicAuth"));
		return openApi;
	}

}
