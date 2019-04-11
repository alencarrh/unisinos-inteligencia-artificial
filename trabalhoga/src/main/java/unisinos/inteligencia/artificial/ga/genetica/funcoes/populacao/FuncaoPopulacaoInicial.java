package unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao;

import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.domain.Geracao;

@Builder
public class FuncaoPopulacaoInicial {

    private final Configuracao configuracao;

    public Geracao gerarPopulacaoInicial() {
        //TODO gerar a população inicial
        //para cada cromossomo gerado, chamar a função de aptidao
        throw new NotImplementedException();
    }

}
