package com.homecafe.config.swagger;

import com.homecafe.config.resolver.LoginMember;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	private final static String securityKey = "BearerKey";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes(securityKey,
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList(securityKey))
				.info(new Info()
						.title("HomeCafe APi Server")
						.version("v0.0.1"));
	}

	static {
		SpringDocUtils.getConfig().addAnnotationsToIgnore(LoginMember.class);
	}

}