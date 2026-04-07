package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.EstruturasDeDados.Listas.Lista;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ControleLivro {
    private final ServicoLivros servicoLivros;
    private final ServicoUsuarios servicoUsuarios;

    private final Scanner scanner;

    public ControleLivro(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        servicoLivros.Adicionar(setLivro(0));

    }

    public void Editar() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        servicoLivros.Editar(setLivro(livro.ID));

    }

    public void Visualizar() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        servicoUsuarios.RegistrarHistórico(livro);

        // criar relações com os livros que o usuario ja visualizacou
        for (var livro2 : servicoUsuarios.GetUsuarioLogado().historicoNavegacao) {
            servicoLivros.InserirRecomendacao(livro, livro2);
        }
        System.out.println(exibeLivro(livro));

    }

    public void Listar() {

        System.out.println("Listando todos os livros");

        for (Livro livro : servicoLivros.Listar()) {
            System.out.println(exibeLivro(livro));
        }

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    public void Remover() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        servicoLivros.Remover(livro);

        System.out.println("Removendo o livro ");

    }

    // EMPRESTIMOS
    public void Emprestar() {

        // procurar o Livro
        Livro livro = buscarLivro();

        if (livro == null)
            return;

        System.out.println(livro.toString());

        // procurar o usuario
        Usuario usuario = ControleUsuario.BuscarUsuarioID(servicoUsuarios, scanner);

        if (usuario == null) {
            return;
        }

        System.out.println(usuario.toString());

        try {
            // Realizar o empréstimo
            int posicao = servicoLivros.Emprestar(livro, usuario);

            if (posicao == 0) {
                // se posicao for zero
                System.out.println("Livro emprestado para: " + livro.Locador.Nome);

                // se retorno da posicao for maior que zero
            } else {
                System.out.printf("\n O livro encontra-se emprestado para %s ", livro.Locador.Nome);
                System.out.printf("\n O usuario %s foi inserido na lista de espera na posição %d",
                        livro.FilaEspera.Topo().Nome, posicao);
            }

        } catch (Exception e) {
            System.out.println("Algo não deu certo: " + e.getMessage());
            return;
        }

    }

    // Devolver empréstimo
    public void Devolver() {

        Livro livro = buscarLivro();

        if (livro == null) {
            return;
        }

        System.out.println(exibeLivro(livro));

        servicoLivros.Devolver(livro);
        System.out.printf("\nLivro devolvido para biblioteca");
        System.out.printf("\n Próximo Usuário na fila de espera do livro", livro.FilaEspera.Topo());

    }

    public void VisualizarEmprestimos() {
        System.out.println("Informe o ID do LIVRO para procurar");

        Livro livro = buscarLivro();

        System.out.println(livro.toString());

        System.out.println(exibeFilaEspera(livro));

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    private String exibeFilaEspera(Livro livro) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nUsuarios na Fila de Espera do livro :");
        for (var usuario : livro.FilaEspera) {
            sb.append(String.format(" [%s], ", usuario.Nome));
        }

        return sb.toString();
    }

    public void ListarEmprestimos() {

        System.out.println("Listando todos empréstimos de livros : \n");
        for (var livro : servicoLivros.Listar()) {

            System.out.printf("\n Livro %s / Emprestado para : %s ", livro.Titulo, livro.Locador);

            System.out.println(exibeFilaEspera(livro));

        }

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    // recomendacoes
    public void ListarRecomendacoes() {

        for (var livro : servicoLivros.Listar()) {

            System.out.println(exibeRecomendacões(livro));
        }

        System.out.println("pressione qualquer tecla para prosseguir ...");
        scanner.nextLine();
    }

    public void VisualizarRecomendacoes() {
        Livro livro = BuscarLivro(servicoLivros, scanner);

        System.out.println(exibeRecomendacões(livro));

        System.out.println("pressione qualquer tecla para prosseguir ...");
        scanner.nextLine();
    }

    // exibições
    private String exibeRecomendacões(Livro livro) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n Recomendacoes para 4[%s]", livro.Titulo));
        for (Livro recomendacao : servicoLivros.VisualizarRecomendacoes(livro)) {
            sb.append(String.format("\n >> Livro %s Autor: %s", recomendacao.Titulo, recomendacao.Autor));
        }

        return sb.toString();
    }

    private String exibeLivro(Livro livro) {

        int largura = 55;

        StringBuilder sb = new StringBuilder();

        for (var usuario : livro.FilaEspera) {

            sb.append(usuario.Nome);
        }

        return String.format(
                """
                         %s
                        | ID         : %-40s |
                        | Titulo     : %-40s |
                        | Autor      : %-40s |
                        | Ano        : %-40s |
                        | Locado Para: %-40s |
                        | Fila Espera: %-40s |
                         %s
                        """,
                "-".repeat(largura),
                livro.ID,
                livro.Titulo,
                livro.Autor,
                livro.Ano,
                (livro.Locador != null ? livro.Locador.Nome : "Ninguém"),
                (livro.FilaEspera.Tamanho() > 0 ? sb.toString() : "Sem espera"),
                "-".repeat(largura));

    }

    private Livro setLivro(int id) {
        System.out.println("Informe os dados do livro");

        System.out.print("Digite nome do livro: ");
        String titulo = scanner.nextLine();// ler titulo
        System.out.print("Digite autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite ano do livro: ");
        String ano = scanner.nextLine();

        return new Livro(id, titulo, autor, ano);

    }

    private Livro buscarLivro() {
        return BuscarLivro(this.servicoLivros, this.scanner);
    }

    // metodos staticos
    public static Livro BuscarLivro(ServicoLivros servicoLivros, Scanner scanner) {
        System.out.println("Informe o ID do livro para pesquisar");

        try {
            Livro livro = servicoLivros.BuscarID(scanner.nextLine());
            System.out.println("Livro Encontrado");
            return livro;

        } catch (Exception e) {
            System.out.println("Algo não deu certo: " + e.getMessage());
            return null;
        }

    }

}