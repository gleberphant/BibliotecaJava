package Adaptadores.Repositorios.EmMemoria;

import Dominio.Modelos.Livro;

import java.util.List;

import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.EstruturasDeDados.Listas.Lista;

public class RepositorioLivrosLista extends Lista<Livro> implements IRepositorioLivro<Livro> {

    public void InserirConexao(Livro t1, Livro t2) {
    }

    public List<Livro> ListarConexoes(Livro livro) {
        return null;
    }

}
