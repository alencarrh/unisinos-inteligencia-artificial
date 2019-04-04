package unisinos.inteligencia.artificial.ga.genetica;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.domain.Rota;
import unisinos.inteligencia.artificial.ga.domain.Veiculo;

@Data
@Builder
public class Cromossomo implements Comparable<Cromossomo> {

    private Map<Veiculo, Rota> rotas;
    private Double aptidao;

    @Override
    public int compareTo(final Cromossomo other) {
        return getAptidao().compareTo(other.getAptidao());
    }
}
