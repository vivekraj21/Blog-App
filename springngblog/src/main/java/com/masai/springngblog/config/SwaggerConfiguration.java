package com.masai.springngblog.config;

import java.util.Arrays;
import java.util.List;
import springfox.documentation.service.AuthorizationScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
	}
	
	private List<springfox.documentation.spi.service.contexts.SecurityContext> securityContexts(){
		return Arrays.asList(springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(sf()).build());
	}
	
	private List<SecurityReference> sf(){
		AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
		return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[] {scope}));
	}
	@Bean
	Docket blogApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Blog App")
				.version("1.0")
				.description("API for Blog App")
				.contact(new Contact("Vivek","http://programmingtechie.com","vivek@gmail.com"))
				.license("Apache License Version 2.0")
				.build();
	}
}