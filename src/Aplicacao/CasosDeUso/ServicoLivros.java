package Aplicacao.CasosDeUso;

import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

import java.util.NoSuchElementException;


import Adaptadores.Repositorios.EstruturasDeDados.Listas.ListaEncadeada;

public class ServicoLivros {

    ListaEncadeada<Livro> repositorioLivros;

    public ServicoLivros() {
        

    }

    // RETORNA O ID DO NOVO LIVRO
    public int Adicionar(Livro novo) {

        novo.ID = repositorioLivros.Tamanho() == 0 ? 0 : repositorioLivros.GetUltimo().ID + 1;
        repositorioLivros.Inserir(novo);
        return novo.ID;
    }

    public Livro Visualizar(String stringID) {

        int ID = validaID(stringID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        return Visualizar(ID);
    }

    public Livro Visualizar(int ID) {

        for (Livro livro : repositorioLivros) {
            if (livro.ID == ID) {
                return livro;
            }
        }

        throw new NoSuchElementException("Livro não encontrado");
        // return null;
    }

    public Livro[] Listar() {
        int indice = 0;
        Livro[] livros = new Livro[repositorioLivros.Tamanho()];

        for (var livro : repositorioLivros) {
            livros[indice] = livro;
            indice++;
        }

        return livros;
    }

    public Boolean Editar(String stringID, String titulo, String autor, String ano) {

        int ID = validaID(stringID);
        for (var livro : repositorioLivros) {

            if (livro.ID == ID) {
                livro.Titulo = titulo;
                livro.Autor = autor;
                livro.Ano = ano;
                return true;
            }

        }

        return false;

    }

    public void Remover(String stringID) {

        int ID = validaID(stringID);

        int indice = 0;

        for (var livro : repositorioLivros) {

            if (livro.ID == ID) {
                repositorioLivros.RemoverNo(indice);
                return;
            }
            indice++;
        }

        throw new NoSuchElementException("Livro não encontrado");

    }

    public boolean Emprestar(String livroID, Usuario locador) {

        Livro livro = Visualizar(livroID);

        return Emprestar(livro, locador);

    }

    public boolean Emprestar(Livro livro, Usuario locador) {

        if (livro.Locador == null) {
            livro.Locador = locador;
            return true;
        } else {
            livro.FilaEspera.Inserir(locador);
            return false;
        }

    }

    public void Devolver(Livro livro) {

        livro.Locador = null;
        PassarParaProximo(livro);

    }

    public void PassarParaProximo(Livro livro) {

        if (livro.FilaEspera.Tamanho() > 0) {
            livro.Locador = livro.FilaEspera.Retirar();
        }

    }

    private int validaID(String stringID) {

        try {
            return Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }

}