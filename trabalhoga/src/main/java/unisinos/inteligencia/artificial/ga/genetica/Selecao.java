package unisinos.inteligencia.artificial.ga.genetica;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Selecao {

    private final List<Cromossomo> cromossomos;

}
