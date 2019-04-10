package unisinos.inteligencia.artificial.ga.genetica.criterio.parada;

import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.roteamento.ParametrosRoteamento;

public class CriterioNumeroMaximoGeracoes implements CriterioParada {

    @Override
    public boolean objetivoAlcancado(final Configuracao configuracao, final ParametrosRoteamento parametrosRoteamento,
        final Populacao populacao) {
        return false;
    }
}
