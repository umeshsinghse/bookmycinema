package com.bmc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("${show-service.openapi.dev-url}")
  private String devUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Contact contact = new Contact();
    contact.setEmail("umeshsingh.se@gmail.com");
    contact.setName("bmc");
    contact.setUrl("http://localhost:8083");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Book My Cinema API")
        .version("1.0")
        .contact(contact)
        .description("Book My Cinema API").termsOfService("https://localhost:8083")
        .license(mitLicense);
    return new OpenAPI().info(info).servers(List.of(devServer));
  }
}