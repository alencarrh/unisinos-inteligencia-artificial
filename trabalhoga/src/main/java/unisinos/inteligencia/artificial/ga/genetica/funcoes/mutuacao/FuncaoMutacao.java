package unisinos.inteligencia.artificial.ga.genetica.funcoes.mutuacao;

import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

@Builder
public class FuncaoMutacao {

    private final Configuracao configuracao;

    /**
     * Função responsável por aplicar a mutação em um cromossomo. A mutação acontece conforme sua probabilidade de
     * acontecer foi configurada.
     *
     * @return Cromossomo com uma mutação
     * @see Configuracao
     */
    public Cromossomo aplicarMucatacao(final Cromossomo cromossomo) {
        //TODO acho que tem que sempre chamar e aqui vai verificar se aplica de fato ou não a mutação
        throw new NotImplementedException();
    }

}
