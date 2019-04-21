package unisinos.inteligencia.artificial.ga.genetica.funcoes.mutacao;

import static java.util.Collections.reverse;
import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import lombok.Builder;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.domain.RotaCidade;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.aptidao.FuncaoAptidao;

@Builder
public class FuncaoMutacao {

    private final Configuracao configuracao;
    private final FuncaoAptidao funcaoAptidao;

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

        int option = ThreadLocalRandom.current().nextInt(0, 100);

        int qtd = ThreadLocalRandom.current().nextInt(0, 3);
        for (int j = 0; j < qtd; j++) {
            mutacaoOrdemRota(cromossomo);
        }

        if (option % 11 == 0) {
            adicionaVeiculoSePossivel(cromossomo);
        }
    }


    /**
     * Obtem uma rota aleatória entre as rotas existentes e muda a ordem de cidades.
     */
    private void mutacaoOrdemRota(Cromossomo cromossomo) {
        int i = ThreadLocalRandom.current().nextInt(0, cromossomo.getRotas().size());
        Rota rota = cromossomo.getRotas().get(i);
        rota.setCidades(new ArrayList<>(rota.getCidades()));
        RotaCidade depositoInicio = rota.getCidades().remove(0);
        RotaCidade depositoFinal = rota.getCidades().remove(rota.getCidades().size() - 1);
        shuffle(rota.getCidades());

        rota.getCidades().add(0, depositoInicio);
        rota.getCidades().add(depositoFinal);
    }

    private void adicionaVeiculoSePossivel(final Cromossomo cromossomo) {

        if (configuracao.getNumeroVeiculos() <= cromossomo.getRotas().size()) {
//            já está sendo utilizado o número máximo de veiculos
            mutacaoOrdemRota(cromossomo);
            return;
        }

//         ordena rotas pelas menores entregas

        List<Rota> rotas = cromossomo.getRotas().stream()
            .sorted(Comparator.comparing(rota -> funcaoAptidao.calcularDistancia(rota.getCidades())))
            .collect(Collectors.toList());

        reverse(rotas);

        int produtosNoVeiculo = configuracao.getCapacidadeCaminhao();

        Rota.RotaBuilder rotaBuilder = Rota.builder();
        for (final Rota rota : rotas) {
            rota.setCidades(new ArrayList<>(rota.getCidades()));
            if (rota.getCidades().size() > 3) {
                RotaCidade rotaCidade = rota.getCidades().remove(1);

                if (produtosNoVeiculo - rotaCidade.getQuantidade() < 0) {
                    break;
                }
                produtosNoVeiculo -= rotaCidade.getQuantidade();
                rotaBuilder.cidade(rotaCidade);
            }
        }

        rotas.add(rotaBuilder.build());
        cromossomo.setRotas(rotas);
    }


}
