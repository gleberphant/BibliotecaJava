package Aplicacao.Interfaces;

import Dominio.Modelos.Livro;


public interface IServico{

    public void Config(IRepositorio repositorio);

    public Boolean AdicionarLivro(Livro novo);

    public Livro VisualizarLivro(int id);

    public Livro[] ListarLivros();

    public Boolean EditarLivro(Livro novo);

    public Boolean RemoverLivro(int id);
}