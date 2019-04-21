package unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Builder;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.domain.RotaCidade;
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
    public void aplicarMucatacao(Cromossomo cromossomo) {
        Integer value = ThreadLocalRandom.current().nextInt(0, 101);

        if (value > configuracao.getFatorMutacao()) {
            return;
        }

        int option = ThreadLocalRandom.current().nextInt(0, 2);

//        switch (option) {
//            case 1:
        int qtd = ThreadLocalRandom.current().nextInt(0, 3);
        for (int j = 0; j < qtd; j++) {
            mutacaoOrdemRota(cromossomo);
        }
//            case 2:

//        }

    }


    /**
     * Obtem uma rota aleatória entre as rotas existentes e muda a ordem de cidades.
     */
    private void mutacaoOrdemRota(Cromossomo cromossomo) {
        int i = ThreadLocalRandom.current().nextInt(0, cromossomo.getRotas().size());
        Rota rota = cromossomo.getRotas().get(i);
        rota.setCidades(new ArrayList<>(rota.getCidades()));
        shuffle(rota.getCidades());
    }

//    private void adicionaVeiculoSePossivel(final Cromossomo cromossomo) {
//
//        if (configuracao.getNumeroVeiculos() <= cromossomo.getRotas().size()) {
//            já está sendo utilizado o número máximo de veiculos
//            mutacaoOrdemRota(cromossomo);
//            return;
//        }
//
//        final List<Rota> rotas = new ArrayList<>();
//
//         ordena rotas pelas menores entregas
//        for (int i = 0; i < cromossomo.getRotas().size(); i++) {
//            final Rota rota = cromossomo.getRotas().get(i);
//
//            rota.getCidades().sort((cidade1, cidade2) -> {});
//
//            for (int j = 0; j < rota.getCidades().size(); j++) {
//
//            }
//
//
//        }
//
//    }

}
