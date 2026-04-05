package Aplicacao.Interfaces;

import java.util.List;

import Dominio.Modelos.Livro;

public interface IRepositorioLivro extends Iterable<Livro> {

    // inserir um item
    public int Inserir(Livro dado);

    // retira proximo item da fila
    public Livro Retirar();

    // retira proximo item da fila
    public Livro Ultimo();

    // visualiza proximo item sem remover
    public Livro Topo();

    // remove proximo item
    public void Remover(Livro chave);

    // remove item de indice
    public void Remover(int indice);

    // Inserir uma Conexao
    public void InserirConexao(Livro t1, Livro t2);

    // Lista de recomendacoes

    public List<Livro> ListarConexoes(Livro livro);

    // quantidade de itens no repositorio
    public int Tamanho();

    // converter os dados do repositorio para uma string
    public String toString();

}
