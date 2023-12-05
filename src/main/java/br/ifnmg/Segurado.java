package br.ifnmg;

import java.time.LocalDate;

public class Segurado {
    private String nome;
    private LocalDate dataDeNascimento;
    private Profissao profissao;
    private Sexo sexo;

    /*
     * A data de nascimento deve ser informada no formato "yyyy-MM-dd"
     */
    public Segurado(String nome, String dataDeNascimento, Profissao profissao, Sexo sexo) {
        this.nome = nome;
        this.profissao = profissao;
        this.sexo = sexo;

        try {
            this.dataDeNascimento = LocalDate.parse(dataDeNascimento);        
        } catch (Exception e) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }        
    }  

    public Segurado(String nome, String dataDeNascimento, Sexo sexo) {
        this(nome, dataDeNascimento, null, sexo);
    }        

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return LocalDate.now().getYear() - dataDeNascimento.getYear();
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Profissao getProfissao() {
        return profissao;
    }
}
