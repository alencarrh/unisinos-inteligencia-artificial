package unisinos.inteligencia.artificial.ga.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Configuracao {

    /**
     * Probabilidade de ocorrer uma mutação no momento da repodução de dois cromossomos. <br> 1.0F representa 100% e
     * 0.0F representa 0%;
     */
    private final Float fatorMutacao;

    /**
     * Tamanho da população inicial (número de cromossomos)
     */
    private final Integer populacaoInicial;

    /**
     * Número máximo de gerações
     */
    private final Integer numeroGeracoes;

    /**
     * Número de veículos disponíveis para entregas
     */
    private final Integer numeroVeiculos;

}
