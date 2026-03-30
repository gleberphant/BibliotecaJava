package Dominio.Modelos;

import Aplicacao.CasosDeUso.ListaDeLivros;

public class Usuario implements Comparable<Usuario> {

    public int ID;
    public String Nome;
    public ListaDeLivros listaLeituras; // VAI RECEBER LIVROS

    public Usuario(String nome) {
        this.Nome = nome;
    }

    public String toString() {
        return this.ID + " [Nome]:" + this.Nome + " ";
    }

    @Override
    public int compareTo(Usuario outro) {

        return Integer.compare(this.ID, outro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outro.Titulo);
    }
}
