package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Livro;

public class ControleRecomendacoes {

    private final ServicoLivros servicoLivros;
    //private final ServicoUsuarios servicoUsuarios;

    private final Scanner scanner;

    public ControleRecomendacoes(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        //this.servicoUsuarios = servicoUsuarios;
    }

    // recomendacoes
    public void ListarRecomendacoes() {

        for (var livro : servicoLivros.Listar()) {

            System.out.println(exibeRecomendacões(livro));
        }

    }

    public void VisualizarRecomendacoes() {
        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

        System.out.println(exibeRecomendacões(livro));

    }

    // exibições
    private String exibeRecomendacões(Livro livro) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n Recomendacoes para o Livro : %s", livro.Titulo));
        for (Livro recomendacao : servicoLivros.VisualizarRecomendacoes(livro.ID + "")) {
            sb.append(String.format("\n >> Livro: '%s' Autor: '%s'", recomendacao.Titulo, recomendacao.Autor));
        }

        return sb.toString();
    }

}
