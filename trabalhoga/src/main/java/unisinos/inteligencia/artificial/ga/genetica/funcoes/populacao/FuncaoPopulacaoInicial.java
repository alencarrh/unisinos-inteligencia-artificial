package unisinos.inteligencia.artificial.ga.genetica.funcoes.populacao;

import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Geracao;

@Builder
public class FuncaoPopulacaoInicial {

    private final Configuracao configuracao;

    public Geracao gerarPopulacaoInicial() {

        //1. vizinhos -> reordenar a rota do próprio caminhão
        //2. mutação -> pode pegar a rota de dois caminhos e trocar as cidades que tem o mesmo custo
        //3.

        //TODO gerar a população inicial
        //para cada cromossomo gerado, chamar a função de aptidao
        throw new NotImplementedException();
    }

}
