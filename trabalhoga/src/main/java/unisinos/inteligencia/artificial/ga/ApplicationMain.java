package unisinos.inteligencia.artificial.ga;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Mundo;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioNumeroMaximoGeracoes;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioParada;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento.FuncaoCruzamento;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao.FuncaoPopulacaoInicial;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecaoCompletamenteAleatoria;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecaoManterMelhores;
import unisinos.inteligencia.artificial.ga.instancia.LeitorInstancia;
import unisinos.inteligencia.artificial.ga.roteamento.EncontrarMelhorRota;

public class ApplicationMain {


    public static void main(String[] args) {
        final Mundo munndo = LeitorInstancia.carregarInstancia("nome_do_arquivo");






        final List<Configuracao> configuracoes = randomConfigurations();
        final Map<Configuracao, Cromossomo> melhoresCromossomos = new HashMap<>();

        configuracoes.forEach(configuracao -> {

            final EncontrarMelhorRota encontrarMelhorRota = EncontrarMelhorRota.builder()
                .configuracao(configuracao)
                .funcaoCruzamento(funcaoCruzamento(configuracao))
                .funcaoPopulacaoInicial(funcaoPopulacaoInicial(configuracao))
                .criteriosParadas(criteriosParada())
                .build();

            final Cromossomo melhorRota = encontrarMelhorRota.encontrar();

            melhoresCromossomos.put(configuracao, melhorRota);

        });

        System.out.println(melhoresCromossomos);
    }

    private static FuncaoPopulacaoInicial funcaoPopulacaoInicial(final Configuracao configuracao) {
        return FuncaoPopulacaoInicial.builder().configuracao(configuracao).build();
    }

    private static FuncaoCruzamento funcaoCruzamento(final Configuracao configuracao) {
        return FuncaoCruzamento.builder()
            .funcaoMutacao(FuncaoMutacao.builder().configuracao(configuracao).build())

            //ou qualquer outra função de seleção. ex: FuncaoSelecaoSempreMelhores.
            .funcaoSelecao(new FuncaoSelecaoCompletamenteAleatoria())
            .funcaoSelecao(new FuncaoSelecaoManterMelhores(10))
            .build();
    }


    private static List<CriterioParada> criteriosParada() {
        //TODO revisar
        return asList(new CriterioNumeroMaximoGeracoes());
    }

    private static List<Configuracao> randomConfigurations() {
        //TODO
        throw new NotImplementedException();
    }

}
