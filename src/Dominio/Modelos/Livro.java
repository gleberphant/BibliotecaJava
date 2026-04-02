package Dominio.Modelos;

import java.lang.Comparable;

import Dominio.EstruturasDeDados.Listas.Fila;

public class Livro implements Comparable<Livro> {

    public Integer ID;
    public String Titulo;
    public String Autor;
    public String Categoria;
    public String Ano;

    public Usuario Locador;
    public Fila<Usuario> FilaEspera; // FILA DE ESPERA VAI RECEBER USUARIOS

    public Livro() {
        SetLivro(0, "titulo", "autor", "ano");
    }

    public Livro(int id, String titulo, String autor, String ano) {
        SetLivro(id, titulo, autor, ano);

    }

    public void SetLivro(int id, String titulo, String autor, String ano) {
        this.ID = id;
        this.Titulo = titulo;
        this.Autor = autor;
        this.Ano = ano;
        FilaEspera = new Fila<>();

    }

    public String toString() {
        int largura = 55;

        StringBuilder sb = new StringBuilder();

        for (var usuario : this.FilaEspera) s{

            sb.append(usuario.Nome);
        }

        return String.format(
                """
                         %s
                        | ID         : %-40s |
                        | Titulo     : %-40s |
                        | Autor      : %-40s |
                        | Ano        : %-40s |
                        | Locado Para: %-40s |
                        | Fila Espera: %-40s |
                         %s
                        """,
                "-".repeat(largura),
                this.ID,
                this.Titulo,
                this.Autor,
                this.Ano,
                (this.Locador != null ? this.Locador.Nome : "Ninguém"),
                (this.FilaEspera.Tamanho() > 0 ? sb.toString() : "Sem espera"),
                "-".repeat(largura));
    }

    @Override
    public int compareTo(Livro outroLivro) {

        return Integer.compare(this.ID, outroLivro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outroLivro.Titulo);
    }
}
