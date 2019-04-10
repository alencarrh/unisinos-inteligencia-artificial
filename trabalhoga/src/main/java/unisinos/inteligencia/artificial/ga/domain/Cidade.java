package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cidade {

    private final Integer id;
    private final String nome;
    private final Posicao posicao;
    private final Integer demanda;
    private final List<Cidade> vizinhos;

    public Double distanciaDe(final Cidade cidade) {
        double distDosX = Math.pow(this.posicao.getX() - cidade.posicao.getY(), 2);
        double distDosY = Math.pow(this.posicao.getY() - cidade.posicao.getY(), 2);

        return Math.sqrt(distDosX - distDosY);
    }
}
