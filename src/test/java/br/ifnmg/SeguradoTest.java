package br.ifnmg;

import org.junit.Assert;
import org.junit.Test;

public class SeguradoTest {

  @Test
  public void deveCriarUmNovoSeguradoSemProfissao() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "2002-06-27",
            Sexo.MASCULINO);

    Assert.assertNotNull(segurado);
    Assert.assertEquals("Caio Ferraz", segurado.getNome());
  }

  //CT1
  @Test(expected = IllegalArgumentException.class)
  public void deveCriarUmNovoSeguradoComDataDeNascimentoInvalida() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "2002-06-50",
            Sexo.MASCULINO);
  }

  // CT2
  @Test(expected = IllegalArgumentException.class)
  public void deveRetornarErroAoCalcularValorDoSeguroComValorBaseMIgualA0() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "2002-06-27",
            Sexo.MASCULINO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    calculo.calcularValorDoSeguro(segurado, 0);
  }

  // CT3
  @Test(expected = IllegalArgumentException.class)
  public void deveLancarExcecaoAoCalcularValorDoSeguroQuandoIdadeMenorQue18() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "2012-06-27",
            Sexo.MASCULINO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    calculo.calcularValorDoSeguro(segurado, 10);
  }

  //CT4
  @Test
  public void deveCalcularValorDoSeguroParaMulheresComIdadeMaiorIgualA45AnosEPericulosidadeBaixaDaProfissao() {
    Segurado segurado = new Segurado("Ana Ferraz",
            "1973-06-27",
            new Profissao("Professora", NivelPericulosidade.BAIXO),
            Sexo.FEMININO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    float valor = calculo.calcularValorDoSeguro(segurado, 10);
    Assert.assertTrue(valor > 1);
  }

  //CT5
  @Test
  public void deveCalcularValorDoSeguroParaHomensComIdadeEntre18E30Anos() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "1999-06-27",
            new Profissao("Professor", NivelPericulosidade.MEDIO),
            Sexo.MASCULINO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    float valor = calculo.calcularValorDoSeguro(segurado, 10);
    Assert.assertTrue(valor > 1);
  }

  //CT6
  @Test
  public void deveCalcularValorDoSeguroParaHomensComIdadeEntre30E50AnosEPericulosidadeMediaDaProfissao() {
    Segurado segurado = new Segurado("Caio Ferraz",
            "1990-06-27",
            new Profissao("Professor", NivelPericulosidade.MEDIO),
            Sexo.MASCULINO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    float valor = calculo.calcularValorDoSeguro(segurado, 10);
    Assert.assertTrue(valor > 1);
  }

  //CT7
  @Test
  public void deveCalcularValorDoSeguroParaHomensComIdadeMaiorQue50AnosEPericulosidadeAltaDaProfissao() {
    Profissao profissao = new Profissao("Professor", NivelPericulosidade.ALTO);
    Segurado segurado = new Segurado("Caio Ferraz",
            "1970-06-27",
            profissao,
            Sexo.MASCULINO);
    CalculoDeSeguroDeVida calculo = new CalculoDeSeguroDeVida();

    float valor = calculo.calcularValorDoSeguro(segurado, 10);
    Assert.assertTrue(valor > 1);

    //Só pra cobrir 100% de todas as linhas
    Assert.assertEquals("Professor", profissao.getNome());
  }

}
