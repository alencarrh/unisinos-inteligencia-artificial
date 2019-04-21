package unisinos.inteligencia.artificial.ga;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.singletonList;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.config.ConfiguracaoGerador;
import unisinos.inteligencia.artificial.ga.csvbuilder.CsvBuilder;
import unisinos.inteligencia.artificial.ga.domain.Mundo;
import unisinos.inteligencia.artificial.ga.domain.Resultado;
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
        final String[] files = {"./instancias/eil33.vrp", "./instancias/att48.vrp", "./instancias/eilc76.vrp"};

        for (final String filename : files) {
            final Mundo mundo = LeitorInstancia.carregarInstancia(filename + ".txt");
            ConfiguracaoGerador configuracaoGerador = new ConfiguracaoGerador(mundo, 100);

            int repeticaoComMesmoExecucao = 30;
            final Map<Configuracao, List<Cromossomo>> melhoresCromossomos = new HashMap<>();
            int a = 0;
            while (configuracaoGerador.hasNext()) {
                System.out.println(a++);
                final Configuracao configuracao = configuracaoGerador.next();

                for (int i = 0; i < repeticaoComMesmoExecucao; i++) {

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

//        System.out.println(melhoresCromossomos);

            System.out.println("000000");
            List<Resultado> resultados = new ArrayList<>();
            melhoresCromossomos.forEach((configuracao, cromossomos) -> {
                Cromossomo melhor = cromossomos.stream().sorted().findFirst().get();
                Cromossomo pior = cromossomos.stream().sorted(reverseOrder()).findFirst().get();
                double media = cromossomos.stream()
                    .mapToDouble(Cromossomo::getAptidao)
                    .average().getAsDouble();

                Resultado resultado = new Resultado();
                resultado.setConfiguracao(configuracao);
                resultado.setMelhor(melhor);
                resultado.setPior(pior);
                resultado.setAptidaoMedia(media);

                resultados.add(resultado);

            });

            CsvBuilder<Resultado> csvBuilder = new CsvBuilder<>();

            String result = csvBuilder
                .forElements(resultados)
                .header("Execuções", "População Inicial", "Veiculos Usados Melhor Rota", "Veiculos Usados Pior Rota",
                    "Número máximo veículos", "Fator mutação(0-100%)",
                    "Máximo gerações",
                    "Qtd Melhores", "Qtd Piores", "Aptidão Média", "Aptidão Melhor Cromossomo",
                    "Rota Melhor Cromossomo(qtd->idCidade)",
                    "Aptidão Pior Cromossomo", "Rota Pior Cromossomo(qtd->idCidade)")
                .column(resultado -> repeticaoComMesmoExecucao)
                .column(resultado -> resultado.getConfiguracao().getPopulacaoInicial())
                .column(resultado -> resultado.getMelhor().getRotas().size())
                .column(resultado -> resultado.getPior().getRotas().size())
                .column(resultado -> resultado.getConfiguracao().getNumeroVeiculos())
                .column(resultado -> resultado.getConfiguracao().getFatorMutacao())
                .column(resultado -> resultado.getConfiguracao().getNumeroMaximoGeracoes())
                .column(resultado -> resultado.getConfiguracao().getQtdMelhoresManter())
                .column(resultado -> resultado.getConfiguracao().getQtdPioresManter())
                .column(resultado -> resultado.getAptidaoMedia())
                .column(resultado -> resultado.getMelhor().getAptidao())
                .column(resultado -> resultado.getMelhor().getRotas())
                .column(resultado -> resultado.getPior().getAptidao())
                .column(resultado -> resultado.getPior().getRotas())
                .toString();

            PrintWriter out = new PrintWriter(filename + ".result.txt");
            out.println(result);
            out.close();

        }
//        System.out.println(result);

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
