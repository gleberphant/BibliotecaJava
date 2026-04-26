package Aplicacao.Servicos;

import Dominio.EstruturasDeDados.Listas.Lista;

import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

import java.util.NoSuchElementException;

import Aplicacao.Interfaces.*;

public class ServicoLivros {

    public IRepositorioLivro repositorioLivros;

    public ServicoLivros(IRepositorioLivro repositorio) {
        repositorioLivros = repositorio;

    }

    // RETORNA O ID DO NOVO LIVRO
    public int Adicionar(Livro livro) {

        repositorioLivros.InserirLivro(livro);

        return livro.ID;
    }

    public Livro Visualizar(Usuario usuario, String stringID) {

        if (usuario == null)
            throw new NoSuchElementException("Usuario inválido");

        Livro livro = BuscarID(stringID);

        for (var livro2 : usuario.historicoNavegacao) {

            InserirRecomendacao(livro, livro2);

        }

        usuario.historicoNavegacao.Inserir(livro);

        return livro;
    }

    public Livro BuscarID(String stringID) {

        int ID = validaID(stringID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        Livro livro = repositorioLivros.BuscarID(ID);
        if (livro == null)
            throw new NoSuchElementException("Livro não encontrado");

        return livro;

    }

    public Lista<Livro> Listar() {

        Lista<Livro> lista = repositorioLivros.ListarLivros();

        return lista;
    }

    public Livro Editar(Livro novoLivro) {

        repositorioLivros.Editar(novoLivro);

        return novoLivro;

    }

    public void Remover(Livro livro) {

        for (var item : repositorioLivros) {

            if (item.ID == livro.ID) {
                repositorioLivros.Remover(item);
                return;
            }
        }

        throw new NoSuchElementException("Livro não encontrado");

    }

    // emprestimos
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

        // se livro emprestado e fila de espera vazia, insere na fila
        if (livro.FilaEspera.Topo() == null) {
            livro.FilaEspera.Inserir(locador);
            return posicao;
        }

        // se livro emprestado e e fila não vazia
        // locador é o proximo da fila?
        if (livro.FilaEspera.Topo().ID == locador.ID) {
            livro.Locador = livro.FilaEspera.Retirar();
            return posicao;
        }

        // procura se locador ja existe na fila.

        for (var usuario : livro.FilaEspera) {
            // se locador ja esta na fila não modifica
            if (usuario.ID == locador.ID)
                return posicao;
            posicao++;
        }

        // se locador não está na fila, então adiciona.
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

    // Recomendacoes
    public void InserirRecomendacao(Livro livro1, Livro livro2) {

        repositorioLivros.InserirConexao(livro1, livro2);

        return;
    }

    public Lista<Livro> VisualizarRecomendacoes(String stringID) {

        Livro livro = BuscarID(stringID);

        return repositorioLivros.ListarConexoes(livro);

    }

    private int validaID(String stringID) {

        try {
            return Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }

}