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

        var caminho = buscadores.BuscaEmLargura(grafo.mapaAdjacencias, inicio, fim);

        var lista = buscadores.ConverterParaLista(caminho, inicio, fim);

        System.out.print("\n Caminho encontrado: ");
        System.out.print(lista.toString());

    }

}
