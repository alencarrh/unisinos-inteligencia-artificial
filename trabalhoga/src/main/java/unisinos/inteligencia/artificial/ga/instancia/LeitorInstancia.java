package unisinos.inteligencia.artificial.ga.instancia;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import unisinos.inteligencia.artificial.ga.domain.Cidade;
import unisinos.inteligencia.artificial.ga.domain.Mundo;

public class LeitorInstancia {

    public static Mundo carregarInstancia(final String file) throws IOException {
        final List<Cidade> cidades = new ArrayList<>();
        List<String> linhas;
        Integer capacidadeVeiculo = 0;

        try (Stream<String> lines = Files.lines(Paths.get(file), Charset.forName("UTF-8"))) {
            linhas = lines.collect(Collectors.toList());
        }

        String estado = "INICIO";

        for (String linha : linhas) {
            String[] colunas = linha.split(" ");

            if (colunas.length == 0) {
                continue;
            }

            if ("CAPACITY:".equals(colunas[0])) {
                capacidadeVeiculo = Integer.valueOf(colunas[1]);
                continue;
            }

            if ("NODE_COORD_SECTION".equals(colunas[0])) {
                estado = "CIDADES";
                continue;
            }

            if ("DEMAND_SECTION".equals(colunas[0])) {
                estado = "DEMANDAS";
                continue;
            }

            switch (estado) {
                case "CIDADES":
                    cidades.add(new Cidade(colunas));
                    continue;
                case "DEMANDAS":
                    updateDemandaCidade(cidades, colunas);
                    continue;
            }
        }

        updateVizinhos(cidades);

        final Cidade deposito = cidades.remove(cidades.indexOf(Cidade.builder().id("1").build()));
        return new Mundo(deposito, cidades, capacidadeVeiculo);
    }

    private static void updateVizinhos(final List<Cidade> cidades) {

        for (final Cidade cidade : cidades) {
            for (final Cidade cidadeVizinha : cidades) {
                if (!cidade.equals(cidadeVizinha)) {
                    cidade.getVizinhos().add(cidade);
                }
            }
        }

    }

    private static void updateDemandaCidade(final List<Cidade> cidades, final String[] colunas) {
        cidades.get(cidades.indexOf(Cidade.builder().id(colunas[0]).build())).setDemanda(Integer.valueOf(colunas[1]));
    }

}
