package unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Singular;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.Selecao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecao;

@Builder
public class FuncaoCruzamento {

    @Singular("funcaoSelecao")
    private final List<FuncaoSelecao> funcoesSelecao;
    private final FuncaoMutacao funcaoMutacao;
    private final Configuracao configuracao;

    public Populacao cruzarPopulacao(final Populacao populacao) {
        final List<Cromossomo> novaGeracao = new ArrayList<>();

        funcoesSelecao.forEach(funcaoSelecao -> {

            List<Selecao> selecao = funcaoSelecao.selecionar(populacao);
            List<Cromossomo> filhos = cruzarSelecao(selecao);

            novaGeracao.addAll(filhos);

        });

        return Populacao.builder()
            .cromossomos(novaGeracao)
            .build();
    }

    private List<Cromossomo> cruzarSelecao(final List<Selecao> selecao) {
        return selecao.stream()
            .map(this::cruzar)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    /**
     * Função responsável por fazer o cruzar entre dois cromossomos (pai e mãe)
     *
     * @param selecao Selecao de cromossomos para cruzamento.
     * @return Retorna o resultado do cruzamento (filho) dos cromossomos selecionados
     */
    private List<Cromossomo> cruzar(final Selecao selecao) {
        if (selecao.getCromossomos().size() == 1) {
            //neste caso, é um dos melhores da geração, então somente mantemos este.
            return Collections.singletonList(selecao.getCromossomos().get(0));
        }

        List<Cromossomo> cromossomosFilhos = new ArrayList<>();

        final Cromossomo pai = selecao.getCromossomos().get(0);
        final Cromossomo mae = selecao.getCromossomos().get(1);

        cromossomosFilhos.add(cruzar(pai, mae));
        cromossomosFilhos.add(cruzar(mae, pai));

        return cromossomosFilhos;
    }

    private Cromossomo cruzar(final Cromossomo pai, final Cromossomo mae) {



        funcaoMutacao.aplicarMucatacao(null);

        //TODO
        return Cromossomo.builder().build();
    }

}
