package unisinos.inteligencia.artificial.ga.genetica;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Geracao {

    private final Populacao populacao;
    private final Cromossomo melhorCromossomo;

}
