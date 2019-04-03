package unisinos.inteligencia.artificial.ga.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Posicao {

    private final Integer x;
    private final Integer y;

}
