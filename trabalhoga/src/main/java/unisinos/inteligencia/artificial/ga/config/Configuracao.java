package unisinos.inteligencia.artificial.ga.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Configuracao {

    /**
     * Probabilidade de ocorrer uma mutação no momento da repodução de dois cromossomos. Intervalo de 0% a 100%.
     */
    private final Integer fatorMutacao;

    /**
     * Tamanho da população inicial (número de cromossomos)
     */
    private final Integer populacaoInicial;

    /**
     * Número máximo de gerações
     */
    private final Integer numeroMaximoGeracoes;

    /**
     * Número de veículos disponíveis para entregas
     */
    private final Integer numeroVeiculos;

    /**
     * Quantidade de produtos que o veículo consegue transportar
     */
    private final Integer capacidadeCaminhao;

    private final Integer qtdMelhoresManter;
    private final Integer qtdPioresManter;

}
