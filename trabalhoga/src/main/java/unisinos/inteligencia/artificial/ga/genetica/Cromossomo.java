package unisinos.inteligencia.artificial.ga.genetica;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.domain.Veiculo;

@Data
@Builder
public class Cromossomo {

    //é uma solução

    private final Map<Veiculo, Rota> rotas;

}
