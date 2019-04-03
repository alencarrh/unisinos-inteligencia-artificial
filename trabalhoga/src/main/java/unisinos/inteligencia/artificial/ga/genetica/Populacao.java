package unisinos.inteligencia.artificial.ga.genetica;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class Populacao {

    @Singular
    private final List<Cromossomo> cromossomos;

}
