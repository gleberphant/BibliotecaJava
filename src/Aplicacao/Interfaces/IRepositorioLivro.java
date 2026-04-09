package Aplicacao.Interfaces;

import Dominio.Modelos.Livro;
import Infraestrutura.EstruturasDeDados.Listas.Lista;

public interface IRepositorioLivro extends Iterable<Livro> {

    // inserir um item
    public Livro InserirLivro(Livro dado);

    // Inserir uma Conexao
    public void InserirConexao(Livro t1, Livro t2);

    // Lista de recomendacoes
    public Lista<Livro> ListarConexoes(Livro livro);

    // Lista de livros
    public Lista<Livro> ListarLivros();

    public int Contagem();

    // Editar um livro
    public Livro Editar(Livro novoLivro);

    // Buscar um livro por ID
    Livro BuscarID(int ID);

    // remove proximo item
    public void Remover(Livro chave);

    // quantidade de itens no repositorio
    public int Tamanho();

}
