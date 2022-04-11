package org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //permite que passemos uma ordem para os testes
public class UsuarioControllerTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//somente no teste de controller, pois usamos o padrão rest que utiliza dos verbos e metodos Http -> GET, POST, PUT, DELETE
	@Autowired
	private TestRestTemplate testRestTemplate; //usa os metodos e verbos http, vai testar e vai dar permissão para testar os metodos http
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar apenas um usuário")
	public void deveCadastrarUmUsuario() {
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,"Jose","jose@imperiobronze.com","trabalholindo","https://i.imgur.com/FETvs2O.jpg"));
	 	ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
	
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome()); //vai comparar se o nome passado é igual ao nome da lista 
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario()); // vai comparar se o usuario passado é o mesmo da lista
		
	}
	
	@Test
	@DisplayName("Não permitir duplicação")
	public void naoPermitirDuplicacao() {
		
		
	}

}
