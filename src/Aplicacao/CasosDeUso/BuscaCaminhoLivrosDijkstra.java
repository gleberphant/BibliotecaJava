package Aplicacao.CasosDeUso;

import Dominio.Modelos.Livro;

import Aplicacao.Interfaces.IRepositorioRecomendacoes;
import Dominio.MinhasEstruturasDeDados.Listas.Lista;
import Dominio.MeusAlgoritmos.BuscaEmGrafos;


public class BuscaCaminhoLivrosDijkstra {

    public Lista<Livro> execute(Livro livro1, IRepositorioRecomendacoes repositorio) {
        var grafo = repositorio.GetGrafo().mapaAdjacencias;
        var livro2 = repositorio.GetGrafo().GetPrimeiro();

        BuscaEmGrafos<Livro> busca = new BuscaEmGrafos<>();
        var caminho = busca.BuscaDijkstra(grafo, livro1, livro2);

        return busca.ConverterParaLista(caminho, livro1, livro2);
    }
}
