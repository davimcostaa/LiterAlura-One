package br.com.alura.literalura.Literalura.One.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private Linguagem linguagens;
    private String quantidadeDownload;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<>();

    public Livro() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Linguagem getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(Linguagem linguagens) {
        this.linguagens = linguagens;
    }

    public String getQuantidadeDownload() {
        return quantidadeDownload;
    }

    public void setQuantidadeDownload(String quantidadeDownload) {
        this.quantidadeDownload = quantidadeDownload;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.quantidadeDownload = dadosLivro.quantidadeDownload();
        this.linguagens = Linguagem.valueOf(dadosLivro.linguagens().get(0));
    }

    @Override
    public String toString() {
        return "----Livro---- \n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + getAutores().get(0).getNome() + "\n" +
                "Linguagem: " + linguagens + "\n" +
                "----------"
                ;
    }
}
