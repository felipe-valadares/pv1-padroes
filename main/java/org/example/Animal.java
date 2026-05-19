package org.example;

public class Animal {
    private String nome;
    private boolean adotado;

    public Animal(String nome, boolean adotado) {
        this.nome = nome;
        this.adotado = adotado;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAdotado() {
        return adotado;
    }
}
