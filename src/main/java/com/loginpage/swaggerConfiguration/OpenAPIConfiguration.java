package com.loginpage.swaggerConfiguration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfiguration {


     @Bean
    public OpenAPI defOpenAPI(){

        Server server = new Server();
        server.setUrl("http://localhost:8080/");
        server.setDescription("api/usuario");

        Contact myContact = new Contact();
        myContact.setName("Claiton dos Santos Silva");
        myContact.setEmail("claiton_santos1@hotmail.com");

        Info information = new Info()
        .title("API - Login Page")
        .version("v5.2")
        .description("API de login e cadastro de usuarios.")
        .contact(myContact);

        return new OpenAPI().info(information).servers(List.of(server))
        .components(new Components().addSecuritySchemes("admin", createOAuth2Scheme()))
        .addSecurityItem(new SecurityRequirement());
    }
    private SecurityScheme createOAuth2Scheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(getOAuthFlows());
    }

    private OAuthFlows getOAuthFlows() {
        return new OAuthFlows()
                .authorizationCode(new OAuthFlow()
                    .authorizationUrl("http://localhost:8080/api/usuario") 
                    .tokenUrl("/swagger-ui/index.html/") 
                    .scopes(new Scopes()
                        .addString("read", "Permissão de leitura")
                        .addString("write", "Permissão de escrita")));
    }

}
