package unisinos.inteligencia.artificial.ga.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Configuracao {

    /**
     * Probabilidade de ocorrer uma mutação no momento da repodução de dois cromossomos. Intervalo de 0% a 100%.
     */
    private Integer fatorMutacao = 1;

    /**
     * Tamanho da população inicial (número de cromossomos)
     */
    private Integer populacaoInicial = 200;

    /**
     * Número máximo de gerações
     */
    private Integer numeroMaximoGeracoes = 100;

    /**
     * Número de veículos disponíveis para entregas
     */
    private Integer numeroVeiculos = 10;

    /**
     * Quantidade de produtos que o veículo consegue transportar
     */
    private Integer capacidadeCaminhao;

    private Integer qtdMelhoresManter = 2;
    private Integer qtdPioresManter = 1;

}
