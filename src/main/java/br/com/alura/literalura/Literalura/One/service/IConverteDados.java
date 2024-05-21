package br.com.alura.literalura.Literalura.One.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
