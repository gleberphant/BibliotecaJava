package Dominio.Modelos;

import Dominio.EstruturasDeDados.Listas.Fila;

import java.lang.Comparable;


public class Livro implements Comparable<Livro> {

    public Integer ID;
    public String Titulo;
    public String Autor;
    public String Categoria;
    public int Ano;

    public Fila<Usuario> FilaEspera; // FILA DE ESPERA VAI RECEBER  USUARIOS

    public Livro(String titulo, String autor, int ano) {
        this.ID = 0;
        this.Titulo = titulo;
        this.Autor = autor;
        this.Ano = ano;
        FilaEspera = new Fila<>();
    }

    public String toString() {
        return this.ID + " [Titulo]:" + this.Titulo + " [Autor]: " + this.Autor + " [Ano]:" + this.Ano + " ";
    }

    @Override
    public int compareTo(Livro outroLivro) {

        return Integer.compare(this.ID, outroLivro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outroLivro.Titulo);
    }
}
