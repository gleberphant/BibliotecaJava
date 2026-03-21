package Repositories;


public interface IRepositorio{

    
    public IRepositorio();

    public Boolean AdicionarLivro(Livro novo);

    public Livro VisualizarLivro();

    public Livro[] ListarLivros();

    public Boolean EditarLivro(Livro novo);

    public Boolean RemoverLivro(int id);
}