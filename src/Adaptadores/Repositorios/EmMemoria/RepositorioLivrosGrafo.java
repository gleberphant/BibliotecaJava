package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;
import java.util.List;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.EstruturasDeDados.Grafos.GrafoHash;
import Dominio.Modelos.Livro;

public class RepositorioLivrosGrafo implements IRepositorioLivro {

    private GrafoHash<Livro> grafo;

    public RepositorioLivrosGrafo() {
        grafo = new GrafoHash<>();
    }

    public int Inserir(Livro livro) {
        if (livro == null)
            return -1;

        livro.ID = grafo.ProximaChave();

        return grafo.InserirNo(livro);
    }

    public void InserirConexao(Livro livro1, Livro livro2) {

        grafo.InserirConexao(livro1, livro2);
    }

    public List<Livro> ListarConexoes(Livro livro) {
        List<Livro> livros;

        livros = grafo.VerConexoes(livro);

        return livros;

    }

    // retira o proximo item da fila - FIFO
    public Livro Retirar() {
        Livro dado = Topo();
        Remover(0);
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
    public void Remover(Livro chave) {
        grafo.Remover(chave);
    }

    // remove item por posicao
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
