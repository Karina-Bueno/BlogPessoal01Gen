package org.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() { // Cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger utilizando a especificação OpenAP
		return new OpenAPI()  //Insere as informações sobre a API (Nome do projeto (Title), Descrição e Versão)
				.info(new Info()
					.title("Projeto Blog Pessoal")
					.description("Projeto Blog Pessoal - Generation Brasil")
					.version("v0.0.1")
				.license(new License() //Insere as informações referentes a licença da API (Nome e Link)
					.name("Generation Brasil")
					.url("https://brasil.generation.org/"))
				.contact(new Contact() //: Insere as informações de contato da pessoa Desenvolvedora (Nome, Site e e E-mail)
					.name("Conteudo Generation")
					.url("https://github.com/conteudoGeneration")
					.email("conteudogeneration@gmail.com")))
				.externalDocs(new ExternalDocumentation() //Insere as informações referentes a Documentações Externas (Github,Gitpage e etc), onde são informados o Nome e o Link.
					.description("Github")
					.url("https://github.com/conteudoGeneration/"));
	}
	
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() { 
		
		return openApi -> {  //Cria um Objeto da Classe OpenAPI, que gera a documentação no Swaggerutilizando a especificação OpenAPI.
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> { //Cria um primeiro looping que fará a leitura de todos os recursos (Paths) através do Método getPaths()
				
				ApiResponses apiResponses = operation.getResponses(); //Cria um Objeto da Classe ApiResponses, que receberá as Respostas HTTP de cada endpoint (Paths) através do método getResponses().
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!")); //Adiciona as novas Respostas no endpoint, substituindo as atuais e acrescentando as demais,
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação"));
			}));
		};
	}
	
	private ApiResponse createApiResponse(String message) { //O Método createApiResponse() adiciona uma descrição (Mensagem) em cada Resposta HTTP.
		
		return new ApiResponse().description(message);
	}
	
}
