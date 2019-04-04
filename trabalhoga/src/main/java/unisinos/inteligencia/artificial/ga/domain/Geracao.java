package unisinos.inteligencia.artificial.ga.domain;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

@Data
@Builder
public class Geracao {

    private final Populacao populacao;
    private final Cromossomo melhorCromossomo;

}
