package unisinos.inteligencia.artificial.ga.genetica.criterio.parada;


public interface CriterioParada {


    /**
     * Método que indica se o objetivo foi alcançado e deve-se para a execução
     */
    boolean objetivoAlcancado();

    /**
     * Método de callback para quando for iniciado a execução.
     */
    default void callbackInicio() {

    }

    /**
     * Método de callback para quando for finalizada a execução.
     */
    default void callbackFim() {

    }

}
