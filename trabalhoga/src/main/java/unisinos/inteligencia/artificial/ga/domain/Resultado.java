package unisinos.inteligencia.artificial.ga.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {

    private Configuracao configuracao;
    private Double aptidaoMedia;
    private Cromossomo melhor;
    private Cromossomo pior;


}
