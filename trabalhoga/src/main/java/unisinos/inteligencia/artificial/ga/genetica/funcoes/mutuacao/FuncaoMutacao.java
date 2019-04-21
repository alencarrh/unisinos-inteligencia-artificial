package unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao;

import static java.util.Collections.shuffle;

import java.util.concurrent.ThreadLocalRandom;

import lombok.Builder;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

@Builder
public class FuncaoMutacao {

    private final Configuracao configuracao;

    /**
     * Função responsável por aplicar a mutação em um cromossomo. A mutação acontece conforme sua probabilidade de
     * acontecer foi configurada.
     *
     * @see Configuracao
     */
    public void aplicarMucatacao(final Cromossomo cromossomo) {
        Integer value = ThreadLocalRandom.current().nextInt(0, 101);

        if (value > configuracao.getFatorMutacao()) {
            return;
        }
        int qtd = ThreadLocalRandom.current().nextInt(0, 3);
        for (int j = 0; j < qtd; j++) {
            mutacaoOrdemRota(cromossomo);
        }

    }


    /**
     * Obtem uma rota aleatória entre as rotas existentes e muda a ordem de cidades.
     */
    public void mutacaoOrdemRota(final Cromossomo cromossomo) {
        int i = ThreadLocalRandom.current().nextInt(0, cromossomo.getRotas().size());
        final Rota rota = cromossomo.getRotas().remove(i);

        shuffle(rota.getCidades());
        cromossomo.getRotas().add(i, rota);
    }

}
