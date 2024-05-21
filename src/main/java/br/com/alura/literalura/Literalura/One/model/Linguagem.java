package br.com.alura.literalura.Literalura.One.model;

public enum Linguagem {
    pt,
    en,
    fr,
    es;

    public static Linguagem fromString(String text) {
        for (Linguagem linguagem : Linguagem.values()) {
            if (linguagem.name().equalsIgnoreCase(text)) {
                return linguagem;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    }


