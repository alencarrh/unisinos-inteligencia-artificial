package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.genetica.Selecao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

@Builder
@AllArgsConstructor
public class FuncaoSelecaoManterMelhores implements FuncaoSelecao {

    private final Integer quantidade;

    @Override
    public List<Selecao> selecionar(final Populacao populacao) {
        //TODO retornar casais aleatórios
        throw new NotImplementedException();
    }
}
