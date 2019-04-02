package unisinos.inteligencia.artificial.ga;

public class ApplicationMain {

    /*Algoritmo genético de roteamento de caminhões,
    conforme modelo constante na página 77/79 da aula 4
     */

    /*INDIVIDUO = UMA SOLUÇÃO.  MAS SERÁ UM SOLUÇÃO PARA UMA ÚNICA
    ENTREGA OU UMA SOLUÇÃO COMPLETA PRO GRAFO?
    Coloquei o indivíduo como double inicialmente tratando
    ele como um valor, mas ainda vamos definir de que tipo ele será */

    /*POPULAÇÃO = TODOS OS INDÍVIDUOS "VIVOS" ATUALMENTE, que por sua vez são
    potenciais soluções. Estou tratando-as como list
     */

    //Algoritmo genético de fato.
    //Os demais métodos são seus componentes
    //Não sei ainda o formato de retorno
    public void rotearCaminhoes(populacao){

    }

    private double funcaoDeAptidao(double individuo){
        double grauDeAptidao;

        /*....*/

        return grauDeAptidao;
    }

    private double selecaoAleatoria(List populacao){
       //Embaralha a populacao  e pega o primeiro. Assim faço uma selecao aleatória
        Collections.shuffle(populacao);
        double individuoPraReproducao = populacao.get(1);
        return individuoPraReproducao;
    }

    private double funcaoDeReproducao(double individuoX, double individuoY){
        /*Cruza os indíviduos de acordo com um critério e retorna um "filhote"*/
        double filhote;
        return filhote;
    }

    private double funcaoDeMutacao(double individuo){
        /*Opera a mutação ou não: de acordo com a probabilidade */

        return individuo  //Indivíduo mutado
    }


    public static void main(String[] args) {

        System.out.println("Hello World!");

    }

}
