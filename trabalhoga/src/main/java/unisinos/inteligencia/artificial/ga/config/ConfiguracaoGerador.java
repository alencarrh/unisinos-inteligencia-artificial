package unisinos.inteligencia.artificial.ga.config;

public class ConfiguracaoGerador {

    private Configuracao configuracao;
    private Integer configuracaoNumero = 0;
    private final Integer configuracaoNumeroMaximo;

    public ConfiguracaoGerador(final Integer numeroMaximoConfiguracoes) {
        configuracaoNumeroMaximo = numeroMaximoConfiguracoes;
        configuracao = Configuracao.builder().build();
    }

    private boolean hasNext() {
            return configuracaoNumero < configuracaoNumeroMaximo;
    }

    private Configuracao next() {
        atualizarConfiguracao();

        configuracaoNumero++;
        return configuracao;
    }

    private void atualizarConfiguracao() {

    }


}
