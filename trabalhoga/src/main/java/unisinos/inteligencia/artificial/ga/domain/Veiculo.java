package unisinos.inteligencia.artificial.ga.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Veiculo {

    private final Integer id;
    private final String name;
    private final Integer capacidade;

}
