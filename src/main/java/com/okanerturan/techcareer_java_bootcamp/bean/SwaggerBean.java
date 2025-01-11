package com.okanerturan.techcareer_java_bootcamp.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerBean {

    @Bean
    public OpenAPI getOpenAPIMethod() {
        return new OpenAPI().info(new Info()
                .title("Todo API Documentation")
                .version("1.0")
                .summary("Todo Application API")
                .description("REST API documentation for Todo Application")
                .termsOfService("https://swagger.io/terms/")
                .contact(new Contact()
                        .name("Okan Erturan")
                        .email("okanert@gmail.com")
                        .url("https://github.com/okturan")
                )
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")
                )
        ).servers(List.of(new Server().url("http://localhost:4444")));
    }
}
