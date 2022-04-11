package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //pode rodar em uma porta randomica
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //para conseguir instanciar e testar 
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "DJ Cleiton Rasta", "cleitinho@pedra.com", "cabecadegelo", "https://i.imgur.com/FETvs2O.jpg\r\n"));//0L = valor padrão
		usuarioRepository.save(new Usuario(0L, "DJ Laurinha Lero", "larinha@lera.com", "laura123", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		usuarioRepository.save(new Usuario(0L, "Ednaldo Pereira", "ednaldo@banido.com", "naovalenada", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		usuarioRepository.save(new Usuario(0L, "Mc Naninha", "naninha@imperiobronze.com", "trabalholindo", "https://i.imgur.com/FETvs2O.jpg\r\n"));
		
	}
	
	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("naninha@imperiobronze.com");
		assertTrue(usuario.get().getUsuario().equals("naninha@imperiobronze.com"));
	
	}
	
	@Test
	@DisplayName("Retorna dois usuários")
	public void deveRetornarDoisUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("DJ"); //traz informações em comum entre todos os usuarios
		assertEquals(2, listaDeUsuarios.size()); //compara se a listaDeUsuarios.size é igual a 2, caso for maior vai me informar que o resultado é maior
	
		//pega um arrai de nomes
		assertTrue(listaDeUsuarios.get(0).getNome().equals("DJ Cleiton Rasta")); //traz um arranome e verifica se ele bate com a posição do nome da lista
		assertTrue(listaDeUsuarios.get(1).getNome().equals("DJ Laurinha Lero"));
	}

}
