package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ControleEmprestimos {
    private final ServicoLivros servicoLivros;
    private final ServicoUsuarios servicoUsuarios;

    private final Scanner scanner;

    public ControleEmprestimos(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        this.servicoUsuarios = servicoUsuarios;
    }

    // EMPRESTIMOS
    public void Emprestar() {

        // procurar o Livro
        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

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

        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

        if (livro == null) {
            return;
        }

        System.out.println(ControleLivro.exibeLivro(livro));

        servicoLivros.Devolver(livro);
        System.out.printf("\n Livro devolvido para biblioteca");
        System.out.printf("\n Próximo Usuário na fila de espera do livro", livro.FilaEspera.Topo());

    }

    public void VisualizarEmprestimos() {
        System.out.println("Informe o ID do LIVRO para procurar");

        Livro livro = ControleLivro.BuscarLivro(servicoLivros, scanner);

        System.out.println("Exibindo emprestivos no livro \n");
        System.out.println(ControleLivro.exibeFilaEspera(livro));

    }

    public void ListarEmprestimos() {

        System.out.println("Listando todos empréstimos de livros : \n");
        for (var livro : servicoLivros.Listar()) {

            System.out.println(ControleLivro.exibeFilaEspera(livro));

        }

    }

}
