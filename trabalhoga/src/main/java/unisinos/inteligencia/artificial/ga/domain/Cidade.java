package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Data
@Builder
public class Cidade {

    private final Integer id;
    private final String nome;
    private final Posicao posicao;
    private final Integer demanda;
    private final List<Cidade> vizinhos;

    public Integer distanciaDe(final Cidade cidade) {
        //TODO implementar cálculo da distância euclidiana
        throw new NotImplementedException();
    }
}
