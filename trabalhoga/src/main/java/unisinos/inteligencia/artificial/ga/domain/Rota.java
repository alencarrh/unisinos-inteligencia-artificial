package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rota {

    private final List<Cidade> caminho;

}
