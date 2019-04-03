package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mundo {

    private final Cidade deposito;
    private final List<Cidade> cidade;

}
