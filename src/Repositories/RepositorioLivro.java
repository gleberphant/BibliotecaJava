package Repositories;


public class RepositorioLivro implements IRepositorio{
    
    LinkedList ListaLivros;

    RepositorioLivro (){
        ListaLivros = new LinkedList<Livro>

    }
    
    public Boolean AdicionarLivro(Livro novo){
        ListaLivros.add(novo)
        return true;
    }

    public Livro VisualizarLivro(){

        return Livro;

    }

    public Livro[] ListarLivros(){

        return Livro[]

    }

    public Boolean EditarLivro(Livro novo){

        return true;

    }

    public Boolean RemoverLivro(int id){
        return true;
    }




}