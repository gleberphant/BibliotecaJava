package Aplicacao.CasosDeUso;

import java.util.NoSuchElementException;

import Adaptadores.Repositorios.EmMemoria.RepositorioLivros;
import Aplicacao.Interfaces.IRepositorioLivro;
import Dominio.Modelos.Livro;

public class BuscaLivroID {

    public Livro Executar(String stringID, IRepositorioLivro repositorioLivros) {
        int ID;
        try {
            ID = Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        Livro livro = repositorioLivros.BuscarID(ID);
        if (livro == null)
            throw new NoSuchElementException("Livro não encontrado");

        return livro;

    }

}
