package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.List;

import unisinos.inteligencia.artificial.ga.domain.Casal;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

public interface FuncaoSelecao {

    List<Casal> selecionar(final Populacao populacao);

}
