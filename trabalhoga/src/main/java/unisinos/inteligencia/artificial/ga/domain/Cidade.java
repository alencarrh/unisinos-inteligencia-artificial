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

        Integer distDosX = Math.pow(this.posicao.x - cidade.posicao.x), 2)
        Integer distDosY =  Math.pow(this.posicao.y - cidade.posicao.y, 2)

        return Math.sqrt(distDosX - distDosY)
    }
}
