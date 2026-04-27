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
    public int AdicionarLivro(Livro livro) {

        repositorioLivros.InserirLivro(livro);

        return livro.ID;
    }

    public Livro VisualizarLivro(Usuario usuarioLogado, String livroID) {

        if (usuarioLogado == null)
            throw new NoSuchElementException("usuarioLogado inválido");

        Livro livro1 = BuscarLivroPorID(livroID);

        for (var livro2 : usuarioLogado.historicoNavegacao) {
            // inserir recomendacao
            InserirRecomendacao(livro1, livro2);

        }

        usuarioLogado.historicoNavegacao.Inserir(livro1);

        return livro1;
    }

    public Lista<Livro> ListarLivros() {

        Lista<Livro> lista = repositorioLivros.ListarLivros();

        return lista;
    }

    public Livro EditarLivro(Livro novoLivro) {

        repositorioLivros.Editar(novoLivro);

        return novoLivro;

    }

    public void RemoverLivro(Livro livro) {

        for (var item : repositorioLivros) {

            if (item.ID == livro.ID) {
                repositorioLivros.Remover(item);
                return;
            }
        }

        throw new NoSuchElementException("Livro não encontrado");
    }

    public Livro BuscarLivroPorID(String livroID) {

        int ID = validarLivroID(livroID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        Livro livro = repositorioLivros.BuscarID(ID);
        if (livro == null)
            throw new NoSuchElementException("Livro não encontrado");

        return livro;

    }

    private int validarLivroID(String livroID) {

        try {
            return Integer.parseInt(livroID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }

    // Recomendacoes - talvez eu precise criar um serviço exclusivo para

    public void InserirRecomendacao(Livro livroOrigem, Livro livroDestino) {

        repositorioLivros.InserirConexao(livroOrigem, livroDestino);

        return;
    }

    public Lista<Livro> ListarRecomendacoes(String livroID) {

        Livro livro = BuscarLivroPorID(livroID);

        return repositorioLivros.ListarConexoes(livro);

    }

}