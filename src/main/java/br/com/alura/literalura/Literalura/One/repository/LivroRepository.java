package br.com.alura.literalura.Literalura.One.repository;

import br.com.alura.literalura.Literalura.One.model.Autor;
import br.com.alura.literalura.Literalura.One.model.Linguagem;
import br.com.alura.literalura.Literalura.One.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Livro l JOIN l.autores a")
    List<Autor> autoresRegistrados();

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE a.anoFalecimento > :ano AND a.anoNascimento < :ano")
    List<Autor> autoresVivosEmDeterminadoAno(Integer ano);

    Optional<List<Livro>> findByLinguagem(Linguagem linguagem);

    @Query(value = "SELECT * FROM livros ORDER BY CAST(quantidade_download AS INT) DESC LIMIT 10", nativeQuery = true)
    List<Livro> retornarTop10MaisBaixados();

    @Query("SELECT a from Livro l JOIN l.autores a WHERE a.nome ILIKE %:nomeAutor%")
    List<Autor> buscarAutorPeloNome(String nomeAutor);

}
