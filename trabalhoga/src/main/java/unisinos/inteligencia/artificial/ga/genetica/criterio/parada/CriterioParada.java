package unisinos.inteligencia.artificial.ga.genetica.criterio.parada;


import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;

public interface CriterioParada {


    /**
     * Método que indica se o objetivo foi alcançado e deve-se para a execução
     */
    boolean objetivoAlcancado(
        final Configuracao configuracao,
        final Populacao populacao);

    /**
     * Método de callback para quando for iniciado a execução.
     */
    default void callbackInicio() {
        //não faz nada por padrão
    }

    /**
     * Método de callback para quando for finalizada a execução.
     */
    default void callbackFim() {
        //não faz nada por padrão
    }

}
