package br.com.alura.literalura.Literalura.One.principal;

import br.com.alura.literalura.Literalura.One.model.*;
import br.com.alura.literalura.Literalura.One.repository.LivroRepository;
import br.com.alura.literalura.Literalura.One.service.ConsumoAPI;
import br.com.alura.literalura.Literalura.One.service.ConverteDados;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private LivroRepository repositorio;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Buscar livros pelo título
                    2 - Buscar livros registrados
                    3 - Buscar autores registrados
                    4 - Buscar autores vivos em determinado ano
                    5 - Buscar livros por idioma
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroNaApi();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivrosPorIdioma() {
        System.out.println("Escolha um idioma: ");
        Linguagem linguagem = Linguagem.fromString(leitura.nextLine());

        var livrosEncontrados = repositorio.findByLinguagens(linguagem);

        if(livrosEncontrados.isPresent()) {
            var livros = livrosEncontrados.get();
            livros.forEach(System.out::println);
        } else {
            System.out.println("Livro não encontrada!");
        }
    }

    private void buscarAutoresVivosEmDeterminadoAno() {
        System.out.println("Digite um ano para busca: ");
        try {
            var anoDesejado = leitura.nextInt();
            List<Autor> autoresVivos = repositorio.autoresVivosEmDeterminadoAno(anoDesejado);
            if(autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor encontrado.");
            }
            autoresVivos.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! ");
        }

    }

    private void buscarAutoresRegistrados() {
        List<Autor> autoresRegistrados = repositorio.autoresRegistrados();
        if (autoresRegistrados.isEmpty()) {
            System.out.println("Não há nenhum autor cadastrado!");
        }
        autoresRegistrados.forEach(System.out::println);
    }

    private void buscarLivrosRegistrados() {
        List<Livro> livrosRegistrados = repositorio.findAll();
        if (livrosRegistrados.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        }
        livrosRegistrados.forEach(System.out::println);
    }

    private void buscarLivroNaApi() {
        System.out.println("Digite o nome de um livro para busca: ");
        var nomeLivro = leitura.nextLine();
        try {
            var json = consumo.obterDados("https://gutendex.com/books/?search=" + nomeLivro.replace(" ", "%20"));
            var jsonConvertido = extrairResultados(json, "results");
            DadosLivro dados = conversor.obterDados(jsonConvertido, DadosLivro.class);
            List<DadosAutor> dadosAutor = Collections.singletonList(conversor.obterDados(extrairResultados(jsonConvertido, "authors"), DadosAutor.class));
            Livro livro = new Livro(dados);
            List<Autor> autores = dadosAutor.stream().map(a -> new Autor(a, livro)).toList();
            livro.setAutores(autores);
            repositorio.save(livro);

            System.out.println(livro);
        } catch (NullPointerException e) {
            System.out.println("Livro não encontrado ou não existente. \n ");
        }

    }

    private String extrairResultados(String apiResponse, String objetivo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(apiResponse);
            JsonNode resultsNode = root.path(objetivo);
            return resultsNode.get(0).toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar JSON", e);
        }
    }
}
