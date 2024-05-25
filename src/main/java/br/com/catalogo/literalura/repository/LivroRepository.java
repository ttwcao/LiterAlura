package br.com.catalogo.literalura.repository;

import br.com.catalogo.literalura.model.Autor;
import br.com.catalogo.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

//interface que persiste a classe Livro pelo id do tipo Long
//CRUD operações de criar, ler, editar e excluir.
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAll();

   @Query("SELECT a.nome FROM Autor a")
    List<String> findNomeAutores();

   //@Query("SELECT a.nome FROM Autor a WHERE :ano BETWEEN a.nascAno AND a.mortAno")
   @Query("SELECT a FROM Autor a WHERE a.nascAno IS NOT NULL AND (a.mortAno IS NULL OR :ano <= a.mortAno) AND :ano >= a.nascAno")
    List<Autor> findAutoresEmDeterminadoAno(Integer ano);

//    @Query("SELECT * FROM Livro WHERE %:idioma% MEMBER OF l.idiomas")
//    List<Livro> findLivrosPorIdioma(@Param("idioma")Languages idioma);
}
