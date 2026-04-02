package Dominio.Modelos;

import Dominio.EstruturasDeDados.Listas.Lista;

public class Usuario implements Comparable<Usuario> {

    public int ID;
    public String Nome;
    public String CPF;
    public Lista<Livro> listaLeituras; // VAI RECEBER LIVROS

    public Usuario() {
        SetUsuario(0, "", "");

    }

    public Usuario(int id, String nome, String cpf) {
        SetUsuario(id, nome, cpf);

    }

    public void SetUsuario(int id, String nome, String cpf) {
        this.ID = id;
        this.Nome = nome;
        this.CPF = cpf;
        listaLeituras = new Lista<>();
    }

    public String toString() {
        int largura = 55;

        String leituras = "Sem leituras";

        if (this.listaLeituras.Tamanho() > 0) {
            StringBuilder sb = new StringBuilder();
            for (var livro : listaLeituras) {
                sb.append(livro.Titulo);
            }
        }

        return String.format(
                """
                         %s
                        | ID         : %-40s |
                        | Nome       : %-40s |
                        | CPF        : %-40s |
                        | Leituras   : %-40s |
                         %s
                        """,
                "-".repeat(largura),
                this.ID,
                this.Nome,
                this.CPF,
                leituras,
                "-".repeat(largura));
    }

    @Override
    public int compareTo(Usuario outro) {

        return Integer.compare(this.ID, outro.ID);

        // Alternativa: Ordenação alfabética pelo título
        // return this.titulo.compareTo(outro.Titulo);
    }
}
