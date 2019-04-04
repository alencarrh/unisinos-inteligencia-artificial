package unisinos.inteligencia.artificial.ga.domain;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

@Data
@Builder
public class Casal {

    private final Cromossomo pai;
    private final Cromossomo mae;

}
