package unisinos.inteligencia.artificial.ga.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString(exclude = "vizinhos")
@EqualsAndHashCode(of = "id")
public class Cidade {

    private String id;
    private String nome;
    private Posicao posicao;
    private Integer demanda;
    private List<Cidade> vizinhos;

    public Cidade(String[] valores) {
        id = nome = valores[0];
        posicao = Posicao.builder()
            .x(Integer.valueOf(valores[1]))
            .y(Integer.valueOf(valores[2]))
            .build();

        vizinhos = new ArrayList<>();
    }

    public Double distanciaDe(final Cidade cidade) {
        double distDosX = Math.pow(this.posicao.getX() - cidade.posicao.getX(), 2);
        double distDosY = Math.pow(this.posicao.getY() - cidade.posicao.getY(), 2);

        return Math.sqrt(distDosX + distDosY);
    }

}
