package br.com.alura.livraria.infra;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringfoxSwaggerConfigurations {
	
	private static final String NOME_API = "livraria-api";
	private static final String DESCRICAO_API = "API REST com Spring Boot para um sistema de gestão de uma livraria online.";
	private static final String VERSAO_API = "Versão 1.0";
	private static final String NOME_AUTOR = "Antonio Eloy";
	private static final String SITE_AUTOR = "https://github.com/antonioeloy";
	private static final String EMAIL_AUTOR = "antonioeloy14@gmail.com";
	private static final String LICENCA = "MIT License";
	private static final String ENDERECO_LICENCA = "https://github.com/antonioeloy/livraria-api/blob/master/LICENSE";
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))              
          .paths(PathSelectors.any())                          
          .build()
          .globalRequestParameters(Arrays.asList(
        		  new RequestParameterBuilder()
        		  .name("Authorization")
        		  .description("Bearer Token")
        		  .required(false)
        		  .in("header")
        		  .build())
        		  )
          .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	    NOME_API, 
	    DESCRICAO_API, 
	    VERSAO_API,
	    null,
	    new Contact(NOME_AUTOR, SITE_AUTOR, EMAIL_AUTOR), 
	    LICENCA, ENDERECO_LICENCA, Collections.emptyList());
	}

}
