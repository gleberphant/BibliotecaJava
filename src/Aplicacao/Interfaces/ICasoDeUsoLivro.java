package Aplicacao.Interfaces;

import Dominio.Modelos.Livro;

public interface ICasoDeUsoLivro {
       public Livro Executar(String stringID, IRepositorioLivro repositorioLivros);
}
