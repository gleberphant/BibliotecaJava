package Adaptadores.Repositorios.EmMemoria;


import java.util.Iterator;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.EstruturasDeDados.Arvores.ArvoreBinaria;
import Dominio.EstruturasDeDados.Grafos.GrafoHash;
import Dominio.EstruturasDeDados.Listas.Lista;
import Dominio.Modelos.Livro;

public class RepositorioLivros implements IRepositorioLivro {

    private GrafoHash<Livro> grafoRecomendacoes;
    private ArvoreBinaria<Livro> indicesLivros;

    int contagem;

    public RepositorioLivros() {
        grafoRecomendacoes = new GrafoHash<>();
    }

    public GrafoHash<Livro> GetGrafo() {
        return grafoRecomendacoes;

    }

    public Livro InserirLivro(Livro livro) {
        if (livro == null)
            return null;

        livro.ID = contagem++;

        return grafoRecomendacoes.InserirItem(livro);

    }

    public void InserirConexao(Livro livro1, Livro livro2) {
        grafoRecomendacoes.InserirConexao(livro1, livro2);
    }

    public Lista<Livro> ListarConexoes(Livro livro) {

        Lista<Livro> lista = new Lista<>();

        for (var item : grafoRecomendacoes.VerConexoes(livro).keySet()) {
            lista.Inserir(item);
        }
        return lista;

    }

    public Lista<Livro> ListarLivros() {

        Lista<Livro> lista = new Lista<>();

        for (var livro : grafoRecomendacoes) {
            lista.Inserir(livro);
        }

        return lista;

    }

    public Livro Editar(Livro novoLivro) {

        for (var livro : grafoRecomendacoes) {
            // se encontrar o livro modifica os dados
            if (livro.ID == novoLivro.ID) {
                livro.Titulo = novoLivro.Titulo;
                livro.Autor = novoLivro.Autor;
                livro.Ano = novoLivro.Ano;
                return livro;
            }
        }
        // se não encontrar o livro insere um novo

        return grafoRecomendacoes.InserirItem(novoLivro);
    }

    public Livro BuscarID(int ID) {

        for (Livro livro : grafoRecomendacoes) {
            if (livro.ID == ID) {
                return livro;
            }
        }

        return null;

    }

    // remove proximo item
    public void Remover(Livro chave) {

        grafoRecomendacoes.RemoverItem(chave);
    }

    public int Tamanho() {
        return grafoRecomendacoes.Tamanho();
    }

    public int Contagem() {
        return contagem;
    }

    public String toString() {
        return grafoRecomendacoes.toString();
    }

    // percorre a fila sem remover
    @Override
    public Iterator<Livro> iterator() {
        // a iteração segue normal pq estou inserindo no fim da lista
        return grafoRecomendacoes.iterator();
    }
}
