package br.com.alura.literalura.Literalura.One.repository;

import br.com.alura.literalura.Literalura.One.model.Autor;
import br.com.alura.literalura.Literalura.One.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT a FROM Livro l JOIN l.autores a")
    List<Autor> autoresRegistrados();
}
