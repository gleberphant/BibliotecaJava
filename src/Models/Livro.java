package Models;

public class Livro implements IModel{

    int id;
    String titulo;
    String autor;
    int ano;


    public Livro(String titulo, String autor, int ano){
        this.titulo=titulo;
        this.autor=autor;
        this.ano=ano;
    }
}


