package Usecases;

import Models.Livro;

public class ServicoLivro implements Servico{

    RepositorioLivro repositorio;


    public ServicoLivro(RepositorioLivro repositorio){
        this.repositorio=repositorio;
    }


    public Boolean AdicionarLivro(Livro novo){
        repositorio.AdicionarLivro(novo);
        return true;
    }

    public Livro VisualizarLivro(){
        
        return repositorio.VisualizarLivro();

    }

    public Livro[] ListarLivros(){

        return repositorio.ListaLivros()

    }

    public Boolean EditarLivro(Livro novo){

        return repositorio.EditarLivro(novo);

    }

    public Boolean RemoverLivro(int id){
        return repositorio.RemoverLivro(id);
    }
}