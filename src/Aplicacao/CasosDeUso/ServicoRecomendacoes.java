package Aplicacao.CasosDeUso;

import Aplicacao.Interfaces.*;
import Dominio.Modelos.Livro;
import Dominio.Entidades.*;

public class ServicoRecomendacoes implements IServico {

    IRepositorio repositorio;
    ListaDeLivros listaLivros;

    public ServicoRecomendacoes(IRepositorio repositorio) {
        this.Config(repositorio);
    }

    public void Config(IRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Boolean Adicionar(Livro novo) {
        listaLivros.Adicionar(novo);
        return true;
    }

    public Livro Visualizar(int id) {
        return listaLivros.Visualizar(id);
    }

    public Livro[] Listar() {
        return listaLivros.Listar();
    }

    public Boolean Editar(Livro novo) {
        return listaLivros.Editar(novo);
    }

    public Boolean Remover(int id) {
        return listaLivros.Remover(id);
    }
}