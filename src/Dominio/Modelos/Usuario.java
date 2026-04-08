package Dominio.Modelos;

import Dominio.EstruturasDeDados.Listas.*;

public class Usuario implements Comparable<Usuario> {

    public int ID;
    public String Nome;
    public String CPF;
    public String Senha;
    public Pilha<Livro> historicoNavegacao;

    public Usuario() {
        SetUsuario(0, "", "", "");

    }

    public Usuario(int id, String nome, String cpf, String senha) {
        SetUsuario(id, nome, cpf, senha);

    }

    public void SetUsuario(int id, String nome, String cpf, String senha) {
        this.ID = id;
        this.Nome = nome;
        this.CPF = cpf;
        this.Senha = senha;
        historicoNavegacao = new Pilha<>();
    }

    // mostrar string em formato json
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (var livro : this.historicoNavegacao) {
            sb.append(String.format("Livro:'%s', ", livro.Titulo));
        }

        return String.format("""
                {ID: '%d', Nome: '%s', Senha: '%s', Leituras: [%s]}
                """,
                this.ID,
                this.Nome,
                this.Senha,
                sb.toString());
    }

    @Override
    public int compareTo(Usuario outro) {

        return Integer.compare(this.ID, outro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outro.Titulo);
    }
}
