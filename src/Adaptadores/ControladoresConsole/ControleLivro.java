package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.BuscaLivroID;

import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Livro;

public class ControleLivro {
    private final ServicoLivros servicoLivros;
    private final ServicoUsuarios servicoUsuarios;
    // private final IRepositorioLivro repositorioLivros;

    private final Scanner scanner;

    public ControleLivro(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        // this.repositorioLivros = servicoLivros.repositorioLivros;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        var novoLivro = novoLivro();

        servicoLivros.Adicionar(novoLivro);

    }

    public void Editar() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        var novoLivro = novoLivro();

        novoLivro.ID = livro.ID;

        servicoLivros.Editar(novoLivro);

    }

    public void Visualizar() {

        System.out.println("Informe o ID do livro procurado");
        try {

            Livro livro = servicoLivros.Visualizar(servicoUsuarios.GetUsuarioLogado(), scanner.nextLine());
            // servicoUsuarios.RegistrarHistórico(livro);
            System.out.println(exibeLivro(livro));

        } catch (Exception e) {
            System.err.printf("Algo deu errado %s", e.getMessage());

        }

    }

    public void Listar() {

        System.out.println("Listando todos os livros");

        for (Livro livro : servicoLivros.Listar()) {
            System.out.println(exibeLivro(livro));
        }

    }

    public void Remover() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        servicoLivros.Remover(livro);

        System.out.println("Removendo o livro ");

    }

    private Livro novoLivro() {
        System.out.println("Informe os dados do livro");

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();// ler titulo
        System.out.print("Autor : ");
        String autor = scanner.nextLine();
        System.out.print("Ano : ");
        String ano = scanner.nextLine();

        return new Livro(0, titulo, autor, ano);

    }

    private Livro buscarLivro() {
        return BuscarLivro(this.servicoLivros, this.scanner);
    }

    public static Livro BuscarLivro(ServicoLivros servicoLivros, Scanner scanner) {
        System.out.println("Informe o ID do livro para pesquisar");

        try {
            Livro livro = new BuscaLivroID().Executar(scanner.nextLine(), servicoLivros.repositorioLivros);
            System.out.println("Livro Encontrado");
            return livro;

        } catch (Exception e) {
            System.out.println("Algo não deu certo: " + e.getMessage());
            return null;
        }

    }

    public static String exibeFilaEspera(Livro livro) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("\n Livro %s / Emprestado para : %s ", livro.Titulo, livro.Locador));
        sb.append("\n Usuarios na Fila de Espera do livro :");
        for (var usuario : livro.FilaEspera) {
            sb.append(String.format(" [%s], ", usuario.Nome));
        }

        return sb.toString();
    }

    public static String exibeLivro(Livro livro) {

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

}