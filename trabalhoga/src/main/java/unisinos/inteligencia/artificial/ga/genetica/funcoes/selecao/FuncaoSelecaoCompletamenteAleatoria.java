package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.Selecao;

public class FuncaoSelecaoCompletamenteAleatoria implements FuncaoSelecao {


    @Override
    public List<Selecao> selecionar(final Populacao populacao) {
        List<Selecao> selecao = new ArrayList<>();

        while (selecao.size() * 2 < populacao.getCromossomos().size()) {

            final int ipai = ThreadLocalRandom.current().nextInt(0, populacao.getCromossomos().size());
            int imae = ThreadLocalRandom.current().nextInt(0, populacao.getCromossomos().size());

            if (ipai == imae) {
                imae = (imae + 2) % populacao.getCromossomos().size();
            }

            final Cromossomo pai = populacao.getCromossomos().get(ipai);
            final Cromossomo mae = populacao.getCromossomos().get(imae);

            selecao.add(Selecao.builder().cromossomo(pai).cromossomo(mae).build());

        }

        return selecao;
    }
}
