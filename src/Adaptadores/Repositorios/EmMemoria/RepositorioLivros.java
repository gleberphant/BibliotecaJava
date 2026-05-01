package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.MinhasEstruturasDeDados.Arvores.ArvoreBinaria;
import Dominio.MinhasEstruturasDeDados.Grafos.GrafoHash;
import Dominio.MinhasEstruturasDeDados.Listas.Lista;
import Dominio.Modelos.Livro;

// repositorio dos livros
// armazena os livros em uma arvore binária
// armazena as recomendações em um grafo

public class RepositorioLivros implements IRepositorioLivro {

    private ArvoreBinaria<Livro> indicesLivros;
    private GrafoHash<Livro> grafoRecomendacoes;

    int contagem;

    public RepositorioLivros() {
        indicesLivros = new ArvoreBinaria<Livro>();
        grafoRecomendacoes = new GrafoHash<Livro>();

    }

    public Livro InserirLivro(Livro livro) {
        livro.ID = contagem;
        contagem++;
        indicesLivros.Inserir(livro);
        // grafoRecomendacoes.InserirItem(livro);

        return livro;
    }

    public Lista<Livro> ListarLivros(){

        Lista<Livro> lista = new Lista<>();

        for (var livro : indicesLivros) {
            lista.Inserir(livro);
        }

        return lista;

    }

    public Livro EditarLivro(Livro novoLivro) {

        var livro = BuscarLivroPorID(novoLivro.ID+"");
        livro = novoLivro;

        return livro;

    }

    // comparableTo de Livro, compara apenas somente pelo ID
    public Livro BuscarLivroPorID(String ID) {

        return indicesLivros.Buscar(new Livro(ID, null, null, null));

    }

    // por quanto to pegando apenas o primeiro livro da arvore, depois implemento algo melhor
    public Livro BuscarLivroAleatorio(){

        return indicesLivros.iterator().next();
        
    }

    // remove proximo item
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

    // ------- recomendacoes
    public GrafoHash<Livro> GetRecomendacoes() {
        return grafoRecomendacoes;

    }

    public void InserirRecomendacao(Livro livro1, Livro livro2) {
        grafoRecomendacoes.InserirConexao(livro1, livro2);
    }

    public Lista<Livro> ListarRecomendacoes(Livro livro) {

        Lista<Livro> lista = new Lista<>();

        for (var item : grafoRecomendacoes.MapaDeConexoes(livro).keySet()) {
            lista.Inserir(item);
        }
        return lista;

    }

    public Lista<Livro> BuscarCaminho(Livro livro1, Livro livro2) {

        var caminho = grafoRecomendacoes.BuscarCaminho(livro2, livro2);

        // converte caminho em uma lista
        Lista<Livro> lista = new Lista<>();

        Livro ponteiro = livro2;
        while (ponteiro != null) {

            lista.Inserir(ponteiro);

            if (ponteiro.equals(livro1))
                break;

            ponteiro = caminho.get(ponteiro);

        }

        return lista;
    }

    

    //
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
