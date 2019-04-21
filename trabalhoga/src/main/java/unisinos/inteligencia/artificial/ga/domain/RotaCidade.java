package unisinos.inteligencia.artificial.ga.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RotaCidade {

    private Cidade cidade;
    private Integer quantidade;

    @Override
    public String toString() {
        return quantidade + "->" + cidade.getId();
    }
}
