package unisinos.inteligencia.artificial.ga;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioNumeroMaximoGeracoes;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioParada;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoAptidao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoCruzamento;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoSelecao;
import unisinos.inteligencia.artificial.ga.roteamento.EncontrarMelhorRota;

public class ApplicationMain {


    public static void main(String[] args) {

        List<Configuracao> configuracoes = randomConfigurations();
        Map<Configuracao, Cromossomo> melhoresCromossomos = new HashMap<>();

        configuracoes.forEach(conguracao -> {

            final EncontrarMelhorRota encontrarMelhorRota = EncontrarMelhorRota.builder()
                .configuracao(conguracao)
                .criteriosParadas(criteriosParada())
                .funcaoAptidao(new FuncaoAptidao())
                .funcaoCruzamento(new FuncaoCruzamento())
                .funcaoMutacao(new FuncaoMutacao())
                .funcaoSelecao(new FuncaoSelecao())
                .build();

            final Cromossomo melhorRota = encontrarMelhorRota.encontrar();
            melhoresCromossomos.put(conguracao, melhorRota);
        });

        System.out.println(melhoresCromossomos);
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
