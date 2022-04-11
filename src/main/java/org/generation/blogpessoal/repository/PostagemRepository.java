package org.generation.blogpessoal.repository;

import java.util.List;

import org.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //indica que é uma classe de repositório 
public interface PostagemRepository extends JpaRepository<Postagem, Long>{ //<qual o tipo de entidade que estamos trabalhando, qual o tipo de id>

	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);    //consulta pelo titulo da postagem, retorna uma lista

}
