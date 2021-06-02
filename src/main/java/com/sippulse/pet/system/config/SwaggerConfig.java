/**
 * 
 */
package com.sippulse.pet.system.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Fábio Figueiredo da Silva
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sippulse.pet"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Pet-Schedule - Interview SIPPULSE", 
				"Pet-Schedule - Interview SIPPULSE",
				"v1",
				"Terms Of Service Url",
				new Contact("Fabio Figueiredo da Silva", "pet-schedule.xyz", "your_email@gmail.com"),
				"License of API", "License of URL", Collections.emptyList());
	}

}
