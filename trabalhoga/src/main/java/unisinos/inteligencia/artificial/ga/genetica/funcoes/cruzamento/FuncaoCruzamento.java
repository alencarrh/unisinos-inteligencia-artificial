package unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Singular;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.domain.Selecao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecao;

@Builder
public class FuncaoCruzamento {

    @Singular("funcaoSelecao")
    private final List<FuncaoSelecao> funcoesSelecao;
    private final FuncaoMutacao funcaoMutacao;

    public Populacao cruzarPopulacao(final Populacao populacao) {
        final List<Cromossomo> novaGeracao = new ArrayList<>();

        funcoesSelecao.forEach(funcaoSelecao -> {

            List<Selecao> selecao = funcaoSelecao.selecionar(populacao);
            List<Cromossomo> filhos = cruzarCasais(selecao);

            novaGeracao.addAll(filhos);

        });

        return Populacao.builder()
            .cromossomos(novaGeracao)
            .build();
    }

    private List<Cromossomo> cruzarCasais(final List<Selecao> selecao) {
        return selecao.stream()
            .map(this::cruzar)
            .collect(Collectors.toList());
    }

    /**
     * Função responsável por fazer o cruzar entre dois cromossomos (pai e mãe)
     *
     * @param selecao Selecao de cromossomos para cruzamento.
     * @return Retorna o resultado do cruzamento (filho) dos cromossomos selecionados
     */
    private Cromossomo cruzar(final Selecao selecao) {
        if (selecao.getCromossomos().size() == 1) {
            //neste caso, é um dos melhores da geração, então somente mantemos este.
            return selecao.getCromossomos().get(0);
        }

        // 1. faz o cruzamento entre os dois(ou N) cromossomos

        // 2. aplica a função de mutação
        funcaoMutacao.aplicarMucatacao(null);

        //3. retorna o cromossomo
        //TODO
        throw new NotImplementedException();
    }

}
