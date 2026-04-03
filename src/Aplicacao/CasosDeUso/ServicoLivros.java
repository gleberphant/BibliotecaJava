package Aplicacao.CasosDeUso;

import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

import java.util.NoSuchElementException;

import Aplicacao.Interfaces.IRepositorio;

public class ServicoLivros {

    IRepositorio<Livro> repositorioLivros;


    public ServicoLivros(IRepositorio<Livro> repositorio) {
        repositorioLivros = repositorio;

    }

    // RETORNA O ID DO NOVO LIVRO
    public int Adicionar(Livro livro) {

        livro.ID = repositorioLivros.Tamanho() == 0 ? 0 : repositorioLivros.Ultimo().ID + 1;
        repositorioLivros.Inserir(livro);
        return livro.ID;
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
                Remover(indice);
                return;
            }
            indice++;
        }

        throw new NoSuchElementException("Livro não encontrado");

    }

    public void Remover(int indice) {
        repositorioLivros.Remover(indice);
    }

    public int Emprestar(Livro livro, Usuario locador) {
        // se o livro não estiver com ninguem, empresa pra locador, se o locador for o
        // proximo da fila então o livro é empresato para ele. senão adiciona o locador
        // na fila de espera. se o locador ja estiver na fila de esperra não adiciona

        int posicao = 0;
        // se livro nao tem locador então adiciona
        if (livro.Locador == null) {
            livro.Locador = locador;
            return posicao;
        }
        // se locador for proximo da fila, entao passa para ele
        if (livro.FilaEspera.Topo() == locador) {
            livro.Locador = livro.FilaEspera.Retirar();
            return posicao;
        }

        // procura se locador ja existe na fila. se sim nao adiciona e retorna a posicao
        // dele
        for (var usuario : livro.FilaEspera) {
            if (usuario == locador)
                return posicao;
            posicao++;
        }

        // adiciona usuario na lista de espera e retorna posicao
        livro.FilaEspera.Inserir(locador);
        return posicao;

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