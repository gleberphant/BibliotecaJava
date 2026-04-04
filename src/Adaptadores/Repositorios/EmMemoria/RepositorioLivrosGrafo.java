package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;

import Aplicacao.Interfaces.IRepositorio;

import Dominio.EstruturasDeDados.Grafos.GrafoHash;
import Dominio.EstruturasDeDados.Listas.ListaEncadeada;
import Dominio.Modelos.Livro;

public class RepositorioLivrosGrafo implements IRepositorio<Livro> {

    private GrafoHash<Livro> grafo;

    public RepositorioLivrosGrafo() {
        grafo = new GrafoHash<>();
    }

    public boolean Inserir(Livro livro) {
        if (livro == null)
            return false;
        grafo.InserirNo(livro);
        return true;
    }

    // retira o proximo item da fila - FIFO
    public Livro Retirar() {
        Livro dado = Topo();
        Remover();
        return dado;
    }

    // retira último item da fila sem remover
    public Livro Ultimo() {
        return grafo.GetUltimo();
    }

    // visualiza primeiro item da fila sem remover
    public Livro Topo() {
        return grafo.Get(0);
    }

    // remove proximo item
    public void Remover() {
        grafo.Remover(0);
    }

    // remove item por indice
    public void Remover(int indice) {
        grafo.Remover(indice);
    }

    public int Tamanho() {
        return grafo.Tamanho();
    }

    public String toString() {
        return grafo.toString();
    }

    // percorre a fila sem remover
    @Override
    public Iterator<Livro> iterator() {
        // a iteração segue normal pq estou inserindo no fim da lista
        return grafo.iterator();
    }
}
