package br.com.alura.literalura.Literalura.One.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") String quantidadeDownload,
        @JsonAlias("languages") ArrayList<String> linguagens
) {}
