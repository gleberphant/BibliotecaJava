package Repositories;

import java.util.LinkedList;

import Models.Livro;

public class RepositorioLivro implements IRepositorio {

    LinkedList<Livro> ListaLivros;

    public RepositorioLivro() {
        this.Config();
    }

    public void Config() {
        ListaLivros = new LinkedList<Livro>();
    }

    public Boolean AdicionarLivro(Livro novo) {
        
        novo.id = ListaLivros.size() == 0 ? 0 : ListaLivros.getLast().id + 1 ;
        ListaLivros.add(novo);
        return true;
    }

    public Livro VisualizarLivro(int id) {

        Livro livro;

        for (int i = 0; i < ListaLivros.size(); i++) {
            livro = ListaLivros.get(i);
            if (livro.id == id) {
                return livro;
            }
        }

        return null;
    }

    public Livro[] ListarLivros() {

        Livro[] livros = new Livro[ListaLivros.size()];

        for (int i = 0; i < ListaLivros.size(); i++) {
            livros[i] = ListaLivros.get(i);
        }

        return livros;
    }

    public Boolean EditarLivro(Livro novo) {
        Livro livro;

        for (int i = 0; i < ListaLivros.size(); i++) {

            livro = ListaLivros.get(i);

            if (livro.id == novo.id) {
                ListaLivros.set(i, livro);
                return true;
            }
        }

        return false;

    }

    public Boolean RemoverLivro(int id) {
        Livro livro;

        for (int i = 0; i < ListaLivros.size(); i++) {

            livro = ListaLivros.get(i);

            if (livro.id == id) {
                ListaLivros.remove(i);
                return true;
            }

        }

        return false;
    }

}