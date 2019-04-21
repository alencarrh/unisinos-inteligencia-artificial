package unisinos.inteligencia.artificial.ga;

import static java.util.Collections.singletonList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.config.ConfiguracaoGerador;
import unisinos.inteligencia.artificial.ga.domain.Mundo;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioNumeroMaximoGeracoes;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioParada;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento.FuncaoCruzamento;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao.FuncaoPopulacaoInicial;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecaoCompletamenteAleatoria;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecaoManterMelhores;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecaoManterPiores;
import unisinos.inteligencia.artificial.ga.instancia.LeitorInstancia;
import unisinos.inteligencia.artificial.ga.roteamento.EncontrarMelhorRota;

public class ApplicationMain {


    public static void main(String[] args) throws IOException {
        final Mundo mundo = LeitorInstancia.carregarInstancia("./instancias/att48.vrp.txt");
        ConfiguracaoGerador configuracaoGerador = new ConfiguracaoGerador(mundo, 50);

        final Map<Configuracao, List<Cromossomo>> melhoresCromossomos = new HashMap<>();

        while (configuracaoGerador.hasNext()) {
            final Configuracao configuracao = configuracaoGerador.next();

            for (int i = 0; i < 30; i++) {

                final EncontrarMelhorRota encontrarMelhorRota = EncontrarMelhorRota.builder()
                    .mundo(mundo)
                    .configuracao(configuracao)
                    .funcaoCruzamento(funcaoCruzamento(configuracao))
                    .funcaoPopulacaoInicial(funcaoPopulacaoInicial(configuracao))
                    .criteriosParadas(criteriosParada())
                    .build();

                final Cromossomo melhorRota = encontrarMelhorRota.encontrar();

                List<Cromossomo> cromossomos = melhoresCromossomos.getOrDefault(configuracao, new ArrayList<>());
                cromossomos.add(melhorRota);
                melhoresCromossomos.put(configuracao, cromossomos);

            }
        }

        System.out.println(melhoresCromossomos);





    }

    private static FuncaoPopulacaoInicial funcaoPopulacaoInicial(final Configuracao configuracao) {
        return FuncaoPopulacaoInicial.builder().configuracao(configuracao).build();
    }

    private static FuncaoCruzamento funcaoCruzamento(final Configuracao configuracao) {
        return FuncaoCruzamento.builder()
            .configuracao(configuracao)
            .funcaoMutacao(FuncaoMutacao.builder().configuracao(configuracao).build())
            .funcaoSelecao(new FuncaoSelecaoCompletamenteAleatoria())
            .funcaoSelecao(new FuncaoSelecaoManterMelhores(configuracao.getQtdMelhoresManter()))
            .funcaoSelecao(new FuncaoSelecaoManterPiores(configuracao.getQtdPioresManter()))
            .build();
    }

    private static List<CriterioParada> criteriosParada() {
        return singletonList(new CriterioNumeroMaximoGeracoes());
    }

}
