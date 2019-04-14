package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class RotaCidade {

    private final Cidade cidade;
    private final Integer quantidade;

}
