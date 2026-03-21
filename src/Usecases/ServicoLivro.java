package Usecases;

import Models.Livro;
import Repositories.IRepositorio;

public class ServicoLivro implements IServico {

    IRepositorio repositorio;

    public ServicoLivro(IRepositorio repositorio) {
        this.Config(repositorio);
    }

    public void Config(IRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Boolean AdicionarLivro(Livro novo) {
        repositorio.AdicionarLivro(novo);
        return true;
    }

    public Livro VisualizarLivro(int id) {
        return repositorio.VisualizarLivro(id);
    }

    public Livro[] ListarLivros() {
        return repositorio.ListarLivros();
    }

    public Boolean EditarLivro(Livro novo) {
        return repositorio.EditarLivro(novo);
    }

    public Boolean RemoverLivro(int id) {
        return repositorio.RemoverLivro(id);
    }
}