package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

@Data
@Builder
public class Selecao {

    private final List<Cromossomo> cromossomos;

}
