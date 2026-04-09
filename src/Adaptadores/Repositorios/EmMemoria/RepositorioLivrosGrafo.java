package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.Modelos.Livro;
import Infraestrutura.EstruturasDeDados.Grafos.GrafoHash;
import Infraestrutura.EstruturasDeDados.Listas.Lista;

public class RepositorioLivrosGrafo implements IRepositorioLivro {

    private GrafoHash<Livro> grafo;
    int contagem;

    public RepositorioLivrosGrafo() {
        grafo = new GrafoHash<>();
    }

    public Livro InserirLivro(Livro livro) {
        if (livro == null)
            return null;

        livro.ID = contagem++;

        return grafo.InserirItem(livro);

    }

    public void InserirConexao(Livro livro1, Livro livro2) {
        grafo.InserirConexao(livro1, livro2);
    }

    public Lista<Livro> ListarConexoes(Livro livro) {

        return grafo.VerConexoes(livro);

    }

    public Lista<Livro> ListarLivros() {

        Lista<Livro> lista = new Lista<>();

        for (var livro : grafo) {
            lista.Inserir(livro);
        }

        return lista;

    }

    public Livro Editar(Livro novoLivro) {

        for (var livro : grafo) {
            // se encontrar o livro modifica os dados
            if (livro.ID == novoLivro.ID) {
                livro.Titulo = novoLivro.Titulo;
                livro.Autor = novoLivro.Autor;
                livro.Ano = novoLivro.Ano;
                return livro;
            }
        }
        // se não encontrar o livro insere um novo

        return grafo.InserirItem(novoLivro);
    }

    public Livro BuscarID(int ID) {

        for (Livro livro : grafo) {
            if (livro.ID == ID) {
                return livro;
            }
        }

        return null;

    }

    // remove proximo item
    public void Remover(Livro chave) {

        grafo.RemoverItem(chave);
    }

    public int Tamanho() {
        return grafo.Tamanho();
    }

    public int Contagem() {
        return contagem;
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
