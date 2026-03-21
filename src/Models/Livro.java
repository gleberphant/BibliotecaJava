package Models;

public class Livro{

    public int id;
    public String titulo;
    public String autor;
    public int ano;


    public Livro(String titulo, String autor, int ano){
        this.titulo=titulo;
        this.autor=autor;
        this.ano=ano;
    }

    public String toString(){
        return this.id+" [Titulo]:"+this.titulo+" [Autor]: "+this.autor+" [Ano]:"+this.ano+" ";
    }
}


