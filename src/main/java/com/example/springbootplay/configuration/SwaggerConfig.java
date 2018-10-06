package com.example.springbootplay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by Tony Ng on 5/10/2018.
 * check what annotation we have https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.springbootplay.webservicecontroller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("test swagger doc title")
				.description("test swagger doc title description")
				//.termsOfServiceUrl("http://localhost:8888")
				.version("1.0")
				//.contact(new Contact("name", "url", "email"))
				.build();
	}
}
