package Dominio.Modelos;

import Dominio.EstruturasDeDados.Listas.*;

public class Usuario implements Comparable<Usuario> {

    public int ID;
    public String Nome;
    public String Senha;
    public Pilha<Livro> historicoNavegacao;
    // public Lista<Livro> listaLeituras;

    public Usuario() {
        SetUsuario(0, "", "");

    }

    public Usuario(int id, String nome, String senha) {
        SetUsuario(id, nome, senha);

    }

    public void SetUsuario(int id, String nome, String senha) {
        this.ID = id;
        this.Nome = nome;
        this.Senha = senha;
        historicoNavegacao = new Pilha<>();
    }

    // mostrar string em formato json
    public String toString() {

        StringBuilder historicoNavegacao = new StringBuilder();

        for (var livro : this.historicoNavegacao) {
            historicoNavegacao.append(String.format("Livro:'%s', ", livro.Titulo));
        }

        return String.format("""
                {ID: '%d', Nome: '%s', Senha: '%s', Leituras: [%s]}
                """,
                this.ID,
                this.Nome,
                this.Senha,
                historicoNavegacao.toString());
    }

    @Override
    public int compareTo(Usuario outro) {

        return Integer.compare(this.ID, outro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outro.Titulo);
    }
}
