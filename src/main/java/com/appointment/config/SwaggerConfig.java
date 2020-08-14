package com.appointment.config;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;


@EnableSwagger2
@Configuration
public class SwaggerConfig{

@Bean
public Docket swaggerSpringDocket() {

    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(Lists.newArrayList(apiKey()))
            .securityContexts(Lists.newArrayList(securityContext()))
            .apiInfo(metaData());


        }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Online University REST API")
                .description("Spring Boot REST API for appointment of lesson" +"\n"
                        +"The back-end server uses Spring Boot with Spring Security for JWT authentication " +
                        "and Spring Data JPA for interacting with database. User can signup new account, or login with username & password." +
                        " User can signup new account, or login with username & password. " +
                        "By User’s role (student, teacher), we authorize the User to access resources (role-based Authorization)")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .contact(new Contact("Vitalii Berkunov", "www.linkedin.com/in/vitalii-berkunov", "vitalikberkunov@gmail.com"))
                .build();
    }





    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}