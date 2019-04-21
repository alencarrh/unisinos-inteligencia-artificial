package unisinos.inteligencia.artificial.ga.genetica.criterio.parada;

import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

public class CriterioNumeroMaximoGeracoes implements CriterioParada {

    private Integer geracao = 0;

    @Override
    public boolean objetivoAlcancado(final Configuracao configuracao, final Populacao populacao) {
        geracao++;
        return geracao >= configuracao.getNumeroMaximoGeracoes();
    }
}
