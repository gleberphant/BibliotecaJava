package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Adaptadores.ExibicaoConsole.ExibicaoConsole;
import Dominio.Modelos.Livro;
import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;

public class ControleRecomendacoes {

    private final ServicoLivros servicoLivros;
    private final ExibicaoConsole exibe;

    private final Scanner scanner;

    public ControleRecomendacoes(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner,
            ExibicaoConsole exibe) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        this.exibe = exibe;
    }

    public void ListarRecomendacoes() {

        for (var livro : servicoLivros.ListarLivros()) {

            System.out.println(exibe.exibeRecomendacoes(livro, servicoLivros.ListarRecomendacoes(livro.ID + "")));
        }

    }

    public void VisualizarRecomendacoes() {
        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

        System.out.println(exibe.exibeRecomendacoes(livro, servicoLivros.ListarRecomendacoes(livro.ID + "")));

    }

    public void BuscarCaminho() {
        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

        for (var item : servicoLivros.BuscarCaminho(livro)) {
            System.out.print(" > " + item + " ");
        }

    }

}
