package unisinos.inteligencia.artificial.ga.config;

import java.util.concurrent.ThreadLocalRandom;

import unisinos.inteligencia.artificial.ga.domain.Mundo;

public class ConfiguracaoGerador {

    private Configuracao configuracao;
    private Integer configuracaoNumero = 0;
    private final Integer configuracaoNumeroMaximo;

    public ConfiguracaoGerador(final Mundo mundo, final Integer numeroMaximoConfiguracoes) {
        configuracaoNumeroMaximo = numeroMaximoConfiguracoes;
        configuracao = Configuracao.builder()
            .capacidadeCaminhao(mundo.getCapacidadeCaminhao())
            .populacaoInicial(100)
            .numeroMaximoGeracoes(50)
            .numeroVeiculos(8)
            .qtdMelhoresManter(2)
            .qtdPioresManter(1)
            .fatorMutacao(1)
            .build();
    }

    public boolean hasNext() {
        return configuracaoNumero < configuracaoNumeroMaximo;
    }

    public Configuracao next() {
        atualizarConfiguracao();
        configuracaoNumero++;
        return configuracao;
    }

    private void atualizarConfiguracao() {
        int config = ThreadLocalRandom.current().nextInt(0, 1000);
        int novoFator = configuracao.getFatorMutacao();
        int numeroMaximoGeracao = configuracao.getNumeroMaximoGeracoes();
        int populacaoInicial = configuracao.getPopulacaoInicial();

        if (config % 5 == 0) {
            novoFator = (configuracao.getFatorMutacao() + 5) % 101;
            if (novoFator == 0) {
                novoFator++;
            }
        }

        if (config % 2 == 0) {
            numeroMaximoGeracao = configuracao.getNumeroMaximoGeracoes() + 10;
        }
        if (config % 3 == 0) {
            populacaoInicial = configuracao.getPopulacaoInicial() + 20;
        }

        configuracao = Configuracao.builder()
            .capacidadeCaminhao(configuracao.getCapacidadeCaminhao())
            .populacaoInicial(populacaoInicial)
            .numeroMaximoGeracoes(numeroMaximoGeracao)
            .numeroVeiculos(configuracao.getNumeroVeiculos())
            .qtdMelhoresManter(configuracao.getQtdMelhoresManter())
            .qtdPioresManter(configuracao.getQtdPioresManter())
            .fatorMutacao(novoFator)
            .build();
    }

}
