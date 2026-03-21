package Usecases;


public interface IServico{

    
    public ServicoLivro(RepositorioLivro repositorio);

    public Boolean AdicionarLivro(Livro novo);

    public Livro VisualizarLivro(int id);

    public Livro[] ListarLivros();

    public Boolean EditarLivro(Livro novo);

    public Boolean RemoverLivro(int id);
}