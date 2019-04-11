package unisinos.inteligencia.artificial.ga.genetica.funcoes.aptidao;

import java.util.List;

import unisinos.inteligencia.artificial.ga.domain.Cidade;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

public class FuncaoAptidao {

    public double calcular(final Cromossomo cromossomo) {

        double distanciaPercorridaTodosCaminhoes = cromossomo.getRotas().stream()
            .map(Rota::getCaminho)
            .map(this::calcularDistancia)
            .mapToDouble(Double::doubleValue)
            .sum();

        cromossomo.setAptidao(distanciaPercorridaTodosCaminhoes);

        return distanciaPercorridaTodosCaminhoes * -1;
    }

    private Double calcularDistancia(final List<Cidade> caminho) {
        double distanciaTotal = 0;

        for (int i = 0; i < caminho.size() - 1; i++) {
            final Cidade cidadeAtual = caminho.get(i);
            final Cidade cidadeSeguinte = caminho.get(i + 1);

            distanciaTotal += cidadeAtual.distanciaDe(cidadeSeguinte);
        }

        return distanciaTotal;
    }

}
