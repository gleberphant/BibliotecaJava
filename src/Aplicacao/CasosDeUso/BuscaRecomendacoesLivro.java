package Aplicacao.CasosDeUso;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.Modelos.Livro;
import Dominio.Algoritmos.BuscaDijkstra;

public class BuscaRecomendacoesLivro {
    private BuscaDijkstra<Livro> algoritmo;

    public Livro Executar(String origemID, String destinoID, IRepositorioLivro repositorioLivros) {
        

        var origem = repositorioLivros.BuscarID(validarLivroID(origemID));
        var destino = repositorioLivros.BuscarID(validarLivroID(destinoID));

        algoritmo.BuscarCaminho(repositorioLivros.GetGrafo(), origem, destino);
        
        
        return null;
    }

    private int validarLivroID(String livroID) {

        try {
            return Integer.parseInt(livroID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }

}
