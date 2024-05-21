package br.com.alura.literalura.Literalura.One.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @ManyToOne
    private Livro livro;

    public Autor() {}

    public Autor(DadosAutor dadosAutor, Livro livro) {
        this.nome = dadosAutor.nome();
        this.anoFalecimento = dadosAutor.anoFalecimento();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.livro = livro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "----Autor---- \n" +
                "Nome: " + nome + "\n" +
                "Ano de nascimento: " + anoNascimento + "\n" +
                "Ano de falecimento: " + anoFalecimento + "\n" +
                "Livro: " + livro.getTitulo() + "\n" +
                "----------"
                ;
    }
}
