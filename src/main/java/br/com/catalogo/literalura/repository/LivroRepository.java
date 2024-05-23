package br.com.catalogo.literalura.repository;

import br.com.catalogo.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//interface que persiste a classe Livro pelo id do tipo Long
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findBytituloContainingIgnoreCase‎(String tituloBusca);

    @Query(value = "SELECT * FROM livros", nativeQuery = true)
    List<Livro> findAlllivros();
}
