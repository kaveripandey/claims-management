package com.cognizant.portal;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableFeignClients
public class MemberPortalApplication {

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MemberPortalApplication.class, args); 
	}

	/**
	 * Swagger Configuration
	 * @return
	 */
	@Bean
	public Docket configureSwagger2(){
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.cognizant.portal"))
					.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * Api Info
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo(){
		return new ApiInfo(
				"Authorization Module",
				"Return Order Management Microservice",
				"1.0",
				"sscp - Programmer Analyst Trainee || Full Stack ...in.linkedin.com",
				new Contact("sscp", "something.com","sscp.anything@cognizant.com"),
				"FSE", "https://hello.sscp.com",
				Collections.emptyList()
		);
	}
}
 