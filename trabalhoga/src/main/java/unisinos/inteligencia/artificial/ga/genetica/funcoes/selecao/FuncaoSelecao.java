package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.List;

import unisinos.inteligencia.artificial.ga.domain.Selecao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

public interface FuncaoSelecao {

    List<Selecao> selecionar(final Populacao populacao);

}
