package unisinos.inteligencia.artificial.ga.genetica.funcoes.selecao;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.Selecao;

@Builder
@AllArgsConstructor
public class FuncaoSelecaoManterMelhores implements FuncaoSelecao {

    private final Integer quantidade;

    @Override
    public List<Selecao> selecionar(final Populacao populacao) {
        return populacao.getCromossomos().stream()
            .sorted()
            .limit(quantidade)
            .map(cromossomo -> Selecao.builder().cromossomo(cromossomo).build())
            .collect(Collectors.toList());
    }
}
