package Aplicacao.CasosDeUso;

import java.util.NoSuchElementException;

import Dominio.Modelos.Livro;
import Aplicacao.Interfaces.IRepositorioLivro;

public class BuscaLivroPorID {

    public Livro Executar(String stringID, IRepositorioLivro repositorioLivros) {

        Livro livro = repositorioLivros.BuscarLivroPorID(stringID);
        if (livro == null)
            throw new NoSuchElementException("Livro não encontrado");

        return livro;

    }

}
