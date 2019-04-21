package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.Selecao;

@Builder
@AllArgsConstructor
public class FuncaoSelecaoManterPiores implements FuncaoSelecao {

    private final Integer quantidade;

    @Override
    public List<Selecao> selecionar(final Populacao populacao) {
        return populacao.getCromossomos().stream()
            .sorted(Comparator.reverseOrder())
            .limit(quantidade)
            .map(cromossomo -> Selecao.builder().cromossomo(cromossomo).build())
            .collect(Collectors.toList());
    }
}
