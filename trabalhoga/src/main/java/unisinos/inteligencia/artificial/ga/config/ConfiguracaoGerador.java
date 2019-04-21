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
        int config = ThreadLocalRandom.current().nextInt(0, 6);
        switch (config) {
            case 0:
                int novoFator = (configuracao.getFatorMutacao() + 5) % 101;
                if (novoFator == 0) {
                    novoFator++;
                }
                configuracao.setFatorMutacao(novoFator);
                break;
            case 2:
                configuracao.setNumeroMaximoGeracoes(configuracao.getNumeroMaximoGeracoes() + 10);
                break;
            case 3:
                configuracao.setPopulacaoInicial(configuracao.getPopulacaoInicial() + 20);
                break;
            case 4:
                configuracao.setQtdMelhoresManter(configuracao.getQtdMelhoresManter() + 5);
                break;
            case 5:
                configuracao.setQtdPioresManter(configuracao.getQtdPioresManter() + 5);
                break;
        }
    }


}
