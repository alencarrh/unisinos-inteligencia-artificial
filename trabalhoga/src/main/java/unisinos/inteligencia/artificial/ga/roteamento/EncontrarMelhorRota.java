package unisinos.inteligencia.artificial.ga.roteamento;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
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

    private final FuncaoCruzamento funcaoCruzamento;
    private final FuncaoPopulacaoInicial funcaoPopulacaoInicial;


    @Setter(value = AccessLevel.NONE)
    private final List<Geracao> geracoes;

    public Cromossomo encontrar() {
        //obtém a primeira geração
        geracoes.add(funcaoPopulacaoInicial.gerarPopulacaoInicial());

        //chama o callback de todos os critérios de parada para indicar que o algoritmo iniciou
        criteriosParadas.forEach(CriterioParada::callbackInicio);

        //este loop somente para quando um dos criterios de parada é atigindo
        while (true) {

            final Populacao novoGeracao = funcaoCruzamento.cruzarPopulacao(ultimaGeracao().getPopulacao());

            if (true) { //verificar os criterios de parada para saber quando parar
                break;
            }
        }

        //chama o callback de todos os critérios de parada para indicar que o algoritmo finalizou
        criteriosParadas.forEach(CriterioParada::callbackFim);

        //TODO determinar retorno
        return null;
    }

    private Geracao ultimaGeracao() {
        return geracoes.get(getGeracaoAtual() - 1);
    }

    private Integer getGeracaoAtual() {
        return geracoes.size();
    }
}
