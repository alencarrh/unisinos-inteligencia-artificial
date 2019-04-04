package unisinos.inteligencia.artificial.ga.genetica.funcoes.cruzamento;

import java.util.List;

import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Casal;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao.FuncaoSelecao;

@Builder
public class FuncaoCruzamento {

    private final FuncaoSelecao funcaoSelecao;
    private final FuncaoMutacao funcaoMutacao;

    public Populacao cruzarPopulacao(
        final Configuracao configuracao,
        final Populacao populacao) {

        List<Casal> casais = funcaoSelecao.selecionar(populacao);

        //1. obter cromossomo pai
        //2. obter cromossomo mãe
        final Cromossomo pai = null;
        final Cromossomo mae = null;

        //3. cruza pai e mae
        Cromossomo filho = cruzar(pai, mae);

        //4. chama função mutacao
        filho = funcaoMutacao.aplicarMucatacao(filho);

        //5. adicionar a nova populacao
        //6. retorna nova geracao

        throw new NotImplementedException();
    }


    /**
     * Função responsável por fazer o cruzar entre dois cromossomos (pai e mãe)
     *
     * @param pai Cromossomo pai.
     * @param mae Cromossomo mãe.
     * @return Retorna o resultado do cruzar (filho) de ambos cromossomos
     */
    private Cromossomo cruzar(final Cromossomo pai, final Cromossomo mae) {
        //TODO
        throw new NotImplementedException();
    }

}
