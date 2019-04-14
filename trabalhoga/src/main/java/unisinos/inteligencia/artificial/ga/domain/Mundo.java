package unisinos.inteligencia.artificial.ga.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Mundo {

    private final Cidade deposito;
    private final List<Cidade> cidades;

    /**
     * Quantidade de produtos que o veículo consegue transportar
     */
    private final Integer capacidadeCaminhao;


}
