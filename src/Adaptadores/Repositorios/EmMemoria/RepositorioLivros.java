package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;


import Aplicacao.Interfaces.IRepositorioLivros;
import Dominio.MinhasEstruturasDeDados.Arvores.ArvoreBinaria;
import Dominio.MinhasEstruturasDeDados.Grafos.GrafoHash;
import Dominio.MinhasEstruturasDeDados.Listas.Lista;
import Dominio.Modelos.Livro;

// repositorio dos livros
// armazena os livros em uma arvore binária
// armazena as recomendações em um grafo

public class RepositorioLivros implements IRepositorioLivros {

    private ArvoreBinaria<Livro> indicesLivros;
    private GrafoHash<Livro> grafoRecomendacoes;

    int contagem;

    public RepositorioLivros() {
        indicesLivros = new ArvoreBinaria<Livro>();
        grafoRecomendacoes = new GrafoHash<Livro>();

    }

    public GrafoHash<Livro> GetGrafo() {
        return grafoRecomendacoes;
    }

    public Livro InserirLivro(Livro livro) {

        contagem++;
        indicesLivros.Inserir(livro);
        grafoRecomendacoes.InserirItem(livro);

        return livro;
    }

    public Lista<Livro> ListarLivros() {

        Lista<Livro> lista = new Lista<>();

        // transforma arvore em lista;
        for (var livro : indicesLivros) {
            lista.Inserir(livro);
        }

        return lista;

    }

    public Livro EditarLivro(Livro novoLivro) {

        var livro = BuscarLivroPorID(novoLivro.ID);
        livro = novoLivro;

        return livro;

    }

    // comparableTo de Livro, compara apenas somente pelo ID
    public Livro BuscarLivroPorID(int ID) {

        return indicesLivros.Buscar(new Livro(ID, null, null, null));

    }

    public Livro Topo() {

        return indicesLivros.iterator().next();
    }

    public Livro BuscarLivroAleatorio() {

        /// percorre a arvore e com 20% de chance de retornar um valor
        for (var item : this) {
            if (Math.random() < 0.2) {
                return item;
            }

        }

        return indicesLivros.iterator().next();

    }

    public void RemoverLivro(Livro livro) {

        indicesLivros.Remover(livro);
        grafoRecomendacoes.RemoverItem(livro);
    }

    public int QuantidadeLivros() {
        return grafoRecomendacoes.Tamanho();
    }

    public int ContagemLivros() {
        return contagem;
    }

    public String toString() {
        return indicesLivros.toString();
    }

    // percorre toda arvore
    @Override
    public Iterator<Livro> iterator() {
        // a iteração segue normal pq estou inserindo no fim da lista
        return indicesLivros.iterator();
    }

}
