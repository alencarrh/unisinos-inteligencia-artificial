package unisinos.inteligencia.artificial.ga.genetica;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import unisinos.inteligencia.artificial.ga.domain.Rota;

@Data
@Builder
public class Cromossomo implements Comparable<Cromossomo> {

    /**
     * Rota 0 indica a rota do primeiro caminhão. Rota 1, segundo caminhão, etc.
     */
    @Singular
    private List<Rota> rotas;
    private Double aptidao;

    @Override
    public int compareTo(final Cromossomo other) {
        return getAptidao().compareTo(other.getAptidao());
    }
}
