package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;

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

        int id = servicoLivros.Adicionar(new Livro());
        setLivro(id);

    }

    public void Editar() {

        Livro livro = getLivro();

        if (livro == null)
            return;
        setLivro(livro.ID);

    }

    public void setLivro(int id) {
        System.out.println("Informe os dados do livro");

        System.out.print("Digite nome do livro: ");
        String titulo = scanner.nextLine();// ler titulo
        System.out.print("Digite autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite ano do livro: ");
        String ano = scanner.nextLine();

        servicoLivros.Editar(String.valueOf(id), titulo, autor, ano);
    }

    public void Visualizar() {

        Livro livro = getLivro();

        if (livro == null)
            return;

        servicoUsuarios.RegistrarHistórico(livro);
        //criar relação
        servicoLivros.InserirRecomendacoes(livro, servicoUsuarios.GetUsuarioLogado());

        System.out.println(ExibeLivro(livro));

    }

    public void Listar() {

        System.out.println("Listando todos os livros");
        Livro[] livros = servicoLivros.Listar();

        for (Livro livro : livros) {
            System.out.println(ExibeLivro(livro));
        }

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    public void Remover() {

        Livro livro = getLivro();

        servicoLivros.Remover(livro.ID);

        System.out.println("Removendo o livro ");

    }

    // Adcionar um empréstimo
    public void Emprestar() {

        // procurar o Livro
        Livro livro = getLivro();

        if (livro == null) {
            return;
        }
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
                System.out.println("Livro emprestado para: " + usuario.Nome);

                // se retorno da posicao for maior que zero
            } else {
                System.out.printf("\n O livro encontra-se emprestado para %s ", livro.FilaEspera.Topo().Nome);
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

        Livro livro = getLivro();

        if (livro == null) {
            return;
        }

        System.out.println(ExibeLivro(livro));

        servicoLivros.Devolver(livro);
        System.out.printf("\nLivro devolvido para biblioteca");
        System.out.printf("\n Próximo Usuário na fila de espera do livro", livro.FilaEspera.Topo());

    }

    //
    public void VisualizarEmprestimos() {
        System.out.println("Informe o ID do LIVRO para procurar");

        Livro livro = getLivro();

        System.out.println(livro.toString());

        System.out.print("\n Usuarios na fila > ");

        for (var usuario : livro.FilaEspera) {
            System.out.print(" [" + usuario.toString() + "] ");
        }

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    public void ListarEmprestimos() {

        for (var livro : servicoLivros.Listar()) {

            System.out.print("\n Livro " + livro.toString() + " FILA > ");

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (var usuario : livro.FilaEspera) {
                sb.append(String.format("%s, ", usuario.Nome));
            }
            sb.append("]");

            System.out.println(sb.toString());
        }

        System.out.println("Pressione Qualquer Tecla para continuar ....");
        scanner.nextLine();
    }

    //
    public String ExibeLivro(Livro livro) {

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

    // metodos staticos

    private Livro getLivro() {
        return BuscarLivro(this.servicoLivros, this.scanner);
    }

    // metodos staticos
    public static Livro BuscarLivro(ServicoLivros servicoLivros, Scanner scanner) {
        System.out.println("Informe o ID do livro para pesquisar");

        try {
            Livro livro = servicoLivros.Visualizar(scanner.nextLine());
            System.out.println("Livro Encontrado");
            return livro;

        } catch (Exception e) {
            System.out.println("Algo não deu certo: " + e.getMessage());
            return null;
        }

    }

}