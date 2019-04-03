package unisinos.inteligencia.artificial.ga.roteamento;

import java.util.List;

import lombok.Builder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unisinos.inteligencia.artificial.ga.config.Configuracao;
import unisinos.inteligencia.artificial.ga.genetica.Cromossomo;
import unisinos.inteligencia.artificial.ga.genetica.criterio.parada.CriterioParada;
import unisinos.inteligencia.artificial.ga.genetica.Populacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoAptidao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoCruzamento;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoMutacao;
import unisinos.inteligencia.artificial.ga.genetica.funcoes.FuncaoSelecao;

@Builder
public class EncontrarMelhorRota {

    private final Configuracao configuracao;

    private final FuncaoAptidao funcaoAptidao;
    private final FuncaoCruzamento funcaoCruzamento;
    private final FuncaoMutacao funcaoMutacao;
    private final FuncaoSelecao funcaoSelecao;

    private final List<CriterioParada> criteriosParadas;
    private final List<Populacao> garacoes;


    public Cromossomo encontrar() {
        //TODO
        throw new NotImplementedException();
    }
}
