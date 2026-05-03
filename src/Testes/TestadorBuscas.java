package Testes;

import Dominio.MeusAlgoritmos.BuscaEmGrafos;
import Dominio.MinhasEstruturasDeDados.Grafos.GrafoHash;

public class TestadorBuscas<T extends Comparable<T>> {
    public static void main(String[] args) {
        new TestadorBuscas<>().Executar();
    }

    public void Executar() {

        GrafoHash<T> grafo = new TestadorGrafos<T>().MockarGrafo(new GrafoHash<>());

        BuscaEmGrafos<T> buscadores = new BuscaEmGrafos<>();

        System.out.print("\n Testar buscar Largura");

        T inicio = grafo.GetAleatorio();
        T fim = grafo.GetAleatorio();

        // se forem iguais re rola ate fica diferente
        while ((inicio == null || fim == null) || (inicio.compareTo(fim) == 0)) {
            inicio = grafo.GetAleatorio();
            fim = grafo.GetAleatorio();
        }

        System.out.print("\n Grafo: \n");
        System.out.print(grafo.toString());

        System.out.print("\n Buscar Menor Caminho entre: [" + inicio + "] e [" + fim + "]");
        var caminho = buscadores.BuscaEmLargura(grafo.mapaAdjacencias, inicio, fim);

        System.out.print("\n Caminho encontrado: ");
        System.out.print(buscadores.CaminhoToString(caminho, inicio, fim));

    }

}
