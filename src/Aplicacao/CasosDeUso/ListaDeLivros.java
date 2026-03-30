package Aplicacao.CasosDeUso;

import Dominio.EstruturasDeDados.Listas.ListaEncadeada;
import Dominio.Modelos.Livro;
import Adaptadores.Repositorios.IRepositorio;


public class ListaDeLivros implements IServico<Livro> {

    IRepositorio repositorio;
    ListaEncadeada<Livro> listaLivros;

    public ListaDeLivros(IRepositorio repositorio) {
        listaLivros = new ListaEncadeada<Livro>();
        this.repositorio = repositorio;

    }

    @Override
    public Boolean Adicionar(Livro novo) {

        novo.ID = listaLivros.Tamanho() == 0 ? 0 : listaLivros.GetUltimo().ID + 1;
        listaLivros.Inserir(novo);
        return true;
    }

    @Override
    public Livro Visualizar(int ID) {

        for (var livro : listaLivros) {
            if (livro.ID == ID) {
                return livro;
            }
        }

        return null;
    }

    @Override
    public Livro[] Listar() {
        int indice = 0;
        Livro[] livros = new Livro[listaLivros.Tamanho()];

        for (var livro : listaLivros) {
            livros[indice] = livro;
            indice++;
        }

        return livros;
    }

    @Override
    public Boolean Editar(Livro novo) {

        int indice = 0;

        for (var livro : listaLivros) {

            if (livro.ID == novo.ID) {
                listaLivros.Set(indice, novo);
                return true;
            }
            indice++;
        }

        return false;

    }

    @Override
    public Boolean Remover(int ID) {
        int indice = 0;

        for (var livro : listaLivros) {

            if (livro.ID == ID) {
                listaLivros.RemoverNo(indice);
                return true;
            }
            indice++;
        }

        return false;

    }

}