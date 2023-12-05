package br.ifnmg;

public class CalculoDeSeguroDeVida {    

    public float calcularAcrescimoPorIdade(Segurado segurado){
        Sexo sexo = segurado.getSexo();
        if(segurado.getIdade() < 18){
            throw new IllegalArgumentException("Segurado menor de idade");
        }        
        if(sexo.equals(Sexo.MASCULINO)){
            return calcularAcerscimoPorIdadeParaHomens(segurado.getIdade());
        } else {
            return calcularAcrescimoPorIdadeParaMulheres(segurado.getIdade());
       }    
    }

    /*
     * A porcentagem de acrescimo é calculada da seguinte forma:
     * 
     * - 10% para seguradas com idade acima de 45 anos
     * - 0% para seguradas com idade abaixo de 45 anos
     */
    private float calcularAcrescimoPorIdadeParaMulheres(int idade){
        float acrescimo = 0;
        if(idade >= 45){
            acrescimo = 0.1f;
        }
        return acrescimo;
    }

    /*
     * A porcentagem de acrescimo é calculada da seguinte forma:
     * 
     * - 15% para segurados com idade entre 30 e 50 anos
     * - 30% para segurados com idade acima de 50 anos
     */
    private float calcularAcerscimoPorIdadeParaHomens(int idade){
        float acrescimo = 0;
        if(idade <= 30){
            acrescimo = 0;
        } else if(idade > 30 && idade < 50){
            acrescimo = 0.15f;
        } else if(idade >= 50){
            acrescimo = 0.3f;
        }
        return acrescimo;
    }

    /*
     * A porcentagem de acrescimo é calculada da seguinte forma:
     * 
     * - 0% para profissões de baixo risco
     * - 20% para profissões de médio risco
     * - 35% para profissões de alto risco
     */
    private float calcularAcrescimoPorProfissao(Segurado segurado){
        float acrescimo = 0;
        Profissao profissao = segurado.getProfissao();
        NivelPericulosidade nivelPericulosidade = profissao.getNivelPericulosidade();

        if(nivelPericulosidade.equals(NivelPericulosidade.BAIXO)){        
            acrescimo = 0f;
        } else if(nivelPericulosidade.equals(NivelPericulosidade.MEDIO)){
            acrescimo = 0.2f;
        } else if(nivelPericulosidade.equals(NivelPericulosidade.ALTO)){
            acrescimo = 0.35f;
        }

        return acrescimo;
    }

    /*
     * O valor do seguro é calculado da seguinte forma:
     * O acrescimo por idade é calculado sobre o valor base
     * O acrescimo por profissão é calculado sobre o valor base + acrescimo por idade
    */
    public float calcularValorDoSeguro(Segurado segurado, float valorBase){
        if (valorBase <= 0) {
            throw new IllegalArgumentException("Valor base inválido");
        }

        float valorDoSeguro = valorBase;
        float acrescimoPorIdade = calcularAcrescimoPorIdade(segurado);
        float acrescimoPorProfissao = calcularAcrescimoPorProfissao(segurado);

        valorDoSeguro += valorDoSeguro * acrescimoPorIdade;
        valorDoSeguro += valorDoSeguro * acrescimoPorProfissao;                
        return valorDoSeguro;
    }
}