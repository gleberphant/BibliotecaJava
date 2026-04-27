package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Adaptadores.ExibicaoConsole.ExibicaoConsole;
import Aplicacao.CasosDeUso.BuscaLivroID;

import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Livro;

public class ControleLivro {
    private final ServicoLivros servicoLivros;
    private final ServicoUsuarios servicoUsuarios;
    private final ExibicaoConsole exibe;
    

    private final Scanner scanner;

    public ControleLivro(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner,ExibicaoConsole exibe) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        // this.repositorioLivros = servicoLivros.repositorioLivros;
        this.servicoUsuarios = servicoUsuarios;
        this.exibe = exibe;
    }

    public void Adicionar() {

        var novoLivro = novoLivro();

        servicoLivros.AdicionarLivro(novoLivro);

    }

    public void Editar() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        var novoLivro = novoLivro();

        novoLivro.ID = livro.ID;

        servicoLivros.EditarLivro(novoLivro);

    }

    public void Visualizar() {

        System.out.println("Informe o ID do livro procurado");
        try {

            Livro livro = servicoLivros.VisualizarLivro(servicoUsuarios.GetUsuarioLogado(), scanner.nextLine());
            // servicoUsuarios.RegistrarHistórico(livro);
            System.out.println(exibe.exibeLivro(livro));

        } catch (Exception e) {
            System.err.printf("Algo deu errado %s", e.getMessage());

        }

    }

    public void Listar() {

        System.out.println("Listando todos os livros");

        for (Livro livro : servicoLivros.ListarLivros()) {
            System.out.println(exibe.exibeLivro(livro));
        }

    }

    public void Remover() {

        Livro livro = buscarLivro();

        if (livro == null)
            return;

        servicoLivros.RemoverLivro(livro);

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


}