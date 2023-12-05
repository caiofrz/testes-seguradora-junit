package br.ifnmg;

public class Profissao {
    private String nome;
    private NivelPericulosidade nivelPericulosidade;

    public Profissao(String nome, NivelPericulosidade nivelPericulosidade) {
        this.nome = nome;
        this.nivelPericulosidade = nivelPericulosidade;
    }

    public String getNome() {
        return nome;
    }

    public NivelPericulosidade getNivelPericulosidade() {
        return nivelPericulosidade;
    }
}
