package Dominio.Entidades;

import Dominio.EstruturasDeDados.Listas.ListaEncadeada;
import Dominio.Modelos.Livro;

public class ListaDeLivros {

    ListaEncadeada<Livro> listaLivros;

    public ListaDeLivros() {
        listaLivros = new ListaEncadeada<Livro>();

    }

    public Boolean AdicionarLivro(Livro novo) {

        novo.ID = listaLivros.Tamanho() == 0 ? 0 : listaLivros.GetUltimo().ID + 1;
        listaLivros.Inserir(novo);
        return true;
    }

    public Livro VisualizarLivro(int ID) {

        for (var livro : listaLivros) {
            if (livro.ID == ID) {
                return livro;
            }
        }

        return null;
    }

    public Livro[] ListarLivros() {
        int indice = 0;
        Livro[] livros = new Livro[listaLivros.Tamanho()];

        for (var livro : listaLivros) {
            livros[indice] = livro;
            indice++;
        }

        return livros;
    }

    public Boolean EditarLivro(Livro novo) {

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

    public Boolean RemoverLivro(int ID) {
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