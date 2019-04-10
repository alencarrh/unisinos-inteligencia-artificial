package unisinos.inteligencia.artificial.ga.genetica;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import unisinos.inteligencia.artificial.ga.domain.Rota;

@Data
@Builder
public class Cromossomo implements Comparable<Cromossomo> {

    private List<Rota> rotas;
    private Double aptidao;

    @Override
    public int compareTo(final Cromossomo other) {
        return getAptidao().compareTo(other.getAptidao());
    }
}
