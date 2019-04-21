package unisinos.inteligencia.artificial.ga.roteamento;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Mundo;
import unisinos.inteligencia.artificial.ga.genetica.Geracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioParada;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento.FuncaoCruzamento;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao.FuncaoPopulacaoInicial;

@Getter
@Builder
public class EncontrarMelhorRota {

    private final Configuracao configuracao;
    private final List<CriterioParada> criteriosParadas;
    private final Mundo mundo;

    private final FuncaoCruzamento funcaoCruzamento;
    private final FuncaoPopulacaoInicial funcaoPopulacaoInicial;

    @Setter(value = AccessLevel.NONE)
    private final List<Geracao> geracoes;

    public Cromossomo encontrar() {
        geracoes.add(funcaoPopulacaoInicial.gerarPopulacaoInicial(mundo));
        criteriosParadas.forEach(CriterioParada::callbackInicio);

        while (true) {
            final Populacao novaGeracao = funcaoCruzamento.cruzarPopulacao(ultimaPopulacao());
            final Cromossomo melhorCromossomo = novaGeracao.getCromossomos().stream().sorted().findFirst().get();

            geracoes.add(Geracao.builder().populacao(novaGeracao).melhorCromossomo(melhorCromossomo).build());

            boolean deveParar = criteriosParadas.stream()
                .anyMatch(criterio -> criterio.objetivoAlcancado(configuracao, ultimaPopulacao()));

            if (deveParar) {
                break;
            }
        }
        criteriosParadas.forEach(CriterioParada::callbackFim);

        return ultimaGeracao().getMelhorCromossomo();
    }

    private Populacao ultimaPopulacao() {
        return ultimaGeracao().getPopulacao();
    }

    private Geracao ultimaGeracao() {
        return geracoes.get(getGeracaoAtual() - 1);
    }

    private Integer getGeracaoAtual() {
        return geracoes.size();
    }
}
