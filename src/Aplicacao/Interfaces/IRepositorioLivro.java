package Aplicacao.Interfaces;

import Dominio.EstruturasDeDados.Listas.Lista;
import Dominio.Modelos.Livro;

public interface IRepositorioLivro extends Iterable<Livro> {

    // inserir um item
    public Livro InserirLivro(Livro dado);

    // Lista de livros
    public Lista<Livro> ListarLivros();

    // Editar um livro
    public Livro EditarLivro(Livro novoLivro);

    // Buscar um livro por ID
    Livro BuscarLivroPorID(String ID);
    
    // pegar um livro aleatorio
    Livro BuscarLivroAleatorio();

    // remove proximo item
    public void RemoverLivro(Livro chave);

    // quantidade de itens no repositorio
    public int ContagemLivros();

    // quantidade de itens no repositorio
    public int QuantidadeLivros();

    // Inserir uma Conexao
    public void InserirRecomendacao(Livro t1, Livro t2);

    // Lista de recomendacoes
    public Lista<Livro> ListarRecomendacoes(Livro livro);

    // algoritmo dijkstra para encontrar relacionamentos
    Lista<Livro> BuscarCaminho(Livro livro1, Livro livro2);

}
