package Adaptadores.Repositorios;

import Aplicacao.Interfaces.IRepositorio;
import Dominio.Modelos.Livro;

public class PersistenciaEmMemoria implements IRepositorio {
    public void Config() {
    }

    public Boolean AdicionarLivro(Livro novo) {
        return true;
    }

    public Livro VisualizarLivro(int id) {
        return null;
    }

    public Livro[] ListarLivros() {
        return null;
    }

    public Boolean EditarLivro(Livro novo) {
        return true;
    }

    public Boolean RemoverLivro(int id) {
        return true;
    }
}
