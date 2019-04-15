package unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Builder;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Cidade;
import unisinos.inteligencia.artificial.ga.domain.Mundo;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.domain.RotaCidade;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Geracao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

@Builder
public class FuncaoPopulacaoInicial {


    private final Configuracao configuracao;

    public Geracao gerarPopulacaoInicial(final Mundo mundo) {
        final List<Cromossomo> cromossomos = new ArrayList<>();
        final int tamanhoPopulacaoInicial = configuracao.getPopulacaoInicial() / 3;

        sortByDemanda(mundo.getCidades());
        shiftRightAndAddTo(mundo, cromossomos, tamanhoPopulacaoInicial);

        sortByDistanceFrom(mundo.getCidades(), mundo.getDeposito());
        shiftRightAndAddTo(mundo, cromossomos, tamanhoPopulacaoInicial);

        shuffle(mundo.getCidades());
        shiftRightAndAddTo(mundo, cromossomos, tamanhoPopulacaoInicial);


        //1. vizinhos -> reordenar a rota do próprio caminhão
        //2. mutação -> pode pegar a rota de dois caminhos e trocar as cidades que tem o mesmo custo
        //3.

        return Geracao.builder().populacao(Populacao.builder().cromossomos(cromossomos).build()).build();
    }


    private void shiftRightAndAddTo(final Mundo mundo, final List<Cromossomo> cromossomos, int timesToShift){
        int times = Math.min(timesToShift, mundo.getCidades().size()-1);

        for (int i = 0; i < times; i++) {
            final Cromossomo cromossomo = criarCromossomo(
                mundo.getDeposito(),
                mundo.getCidades(),
                mundo.getCapacidadeCaminhao(),
                configuracao.getNumeroVeiculos());

            shiftRight(mundo.getCidades());
            cromossomos.add(cromossomo);
        }
    }

    private Cromossomo criarCromossomo(
        final Cidade deposito, final List<Cidade> cidades,
        final Integer capacidadeVeiculo, final Integer numeroVeiculos) {

        final Cromossomo.CromossomoBuilder cromossomo = Cromossomo.builder();
        Rota.RotaBuilder rota;

        int cidadeAtual = 0;
        int demandaRestanteCidadeAtual = cidades.get(cidadeAtual).getDemanda();

        for (int i = 0; i < numeroVeiculos && cidadeAtual < cidades.size(); i++) {
            int produtoNoVeiculo = capacidadeVeiculo;
            Cidade cidade = cidades.get(cidadeAtual);
            rota = Rota.builder();

            //inicia no depósito
            rota.cidade(criarRotaCidade(deposito, 0));

            do {
                //adiciona a cidade atual na Rota
                rota.cidade(criarRotaCidade(cidade, Math.min(produtoNoVeiculo, demandaRestanteCidadeAtual)));

                //Atualiza valores de demanda a quantidade de produtos no veiculo
                demandaRestanteCidadeAtual -= produtoNoVeiculo;
                produtoNoVeiculo -= demandaRestanteCidadeAtual;

                //verfica se cidade já foi atendida e atualiza a cidade atual se necessário
                if (demandaRestanteCidadeAtual <= 0) {
                    cidadeAtual++;
                    if (cidadeAtual >= cidades.size()) {
                        break;
                    }
                    cidade = cidades.get(cidadeAtual);
                    demandaRestanteCidadeAtual = cidade.getDemanda();
                }

            } while (produtoNoVeiculo > 0 && cidadeAtual < cidades.size());

            //termina no depósito
            rota.cidade(criarRotaCidade(deposito, 0));

            cromossomo.rota(rota.build());
        }

        return cromossomo.build();
    }

    private void shiftRight(final List<Cidade> cidades) {
        //remove o último adicionando ele na primeiro posição (faz com que os demais sejam movidos 1 posição a direita)
        cidades.add(0, cidades.remove(cidades.size() - 1));
    }


    private RotaCidade criarRotaCidade(final Cidade cidade, final Integer quantidade) {
        return RotaCidade.builder()
            .cidade(cidade)
            .quantidade(quantidade)
            .build();
    }

    private void sortByDemanda(final List<Cidade> cidades) {
        cidades.sort(Comparator.comparing(Cidade::getDemanda));
    }

    private void sortByDistanceFrom(final List<Cidade> cidades, final Cidade cidadeReferencia) {
        cidades.sort(Comparator.comparing(cidade -> cidade.distanciaDe(cidadeReferencia)));
    }
}
