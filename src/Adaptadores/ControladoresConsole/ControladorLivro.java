package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ListaDeLivros;
import Aplicacao.CasosDeUso.ListaDeUsuarios;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ControladorLivro implements IControlador {
    public ListaDeLivros servicoLivros;
    public ListaDeUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControladorLivro(ListaDeLivros servicoLivros, ListaDeUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
    }

    public void Adicionar() {

        setLivro(servicoLivros.Adicionar(new Livro()));

    }

    public void Editar() {

        Livro livro = getLivro();

        if (livro == null)
            return;

        setLivro(livro.ID);

    }

    public void Visualizar() {

        Livro livro = getLivro();

        if (livro == null)
            return;

        System.out.println(livro);

    }

    public void Listar() {

        System.out.println("Listando todos os livros");
        Livro[] livros = servicoLivros.Listar();

        for (Livro livro : livros) {
            System.out.println(livro.toString());
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do livro para remover");

        servicoLivros.Remover(scanner.nextLine());

        System.out.println("Removendo o livro ");

    }

    /// controle de emprestimos
    // Adcionar um empréstimo
    public void Emprestar() {
        // Try Catch para capturar o erro de conversão para inteiro
        try {

            // procurar livro
            Livro livro = getLivro();

            if (livro == null) {
                return;
            }

            System.out.println(livro.toString());

            // procurar o usuario
            System.out.println("Informe o ID do USUARIO que deseja pegar o livro");
            int idUsuario = Integer.parseInt(scanner.nextLine());
            Usuario usuario = servicoUsuarios.Visualizar(idUsuario);

            if (usuario == null) {
                System.out.println("O usuario procurado não existe");
                return;
            } else
                System.out.println(usuario.toString());

            // Realizar o empréstimo
            if (servicoLivrosLivros.Emprestar(livro, usuario)) {
                System.out.println("livro emprestado");
            } else {
                System.out.println("usuario inserido na lista de espera");
            }

        } catch (NumberFormatException e) {
            System.out.println("Digite uma numeração válida para o ID: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

    }

    // Devolver empréstimo
    public void Devolver() {
        // procurar o livro para devolver
        System.out.println("Informe o ID do LIVRO para devolver");
        int idLivro = Integer.parseInt(scanner.nextLine()); // ler dados
        Livro livro = servicoLivrosLivros.Visualizar(idLivro);
        System.out.println(livro.toString());

        servicoLivrosLivros.Devolver(livro);
        System.out.println("Livro devolvido");

    }

    public void VisualizarEmprestimos() {
        System.out.println("Informe o ID do LIVRO para procurar");

        Livro livro = getLivro();

        System.out.println(livro.toString());

        System.out.print("\n Usuarios na fila > ");

        for (var usuario : livro.FilaEspera) {
            System.out.print(" [" + usuario.toString() + "] ");
        }

        System.out.print("\n");
    }

    public void ListarEmprestimos() {

        for (var livro : servicoLivrosLivros.Listar()) {

            System.out.print("\n Livro " + livro.toString() + " FILA > ");

            for (var usuario : livro.FilaEspera) {
                System.out.print(" [" + usuario.toString() + "] ");
            }

        }

        System.out.print("\n");
    }

    // metodos privados

    private void setLivro(int ID) {
        System.out.println("Informe os dados do livro");
        String id = String.valueOf(ID);
        System.out.print("Digite nome do livro: ");
        String titulo = scanner.nextLine();// ler titulo
        System.out.print("Digite autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite ano do livro: ");
        String ano = scanner.nextLine();

        servicoLivros.Editar(id, titulo, autor, ano);
    }

    private Livro getLivro() {
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

    private Usuario getUsuario() {
        System.out.println("Informe o ID do usuario para pesquisar");

        try {
            Usuario usuario = servicoUsuarios.Visualizar(scanner.nextLine());
            System.out.println("Usuario Encontrado");
            return usuario;

        } catch (Exception e) {
            System.out.println("Algo não deu certo: " + e.getMessage());
            return null;
        }

    }

    private void setUsuario(int ID) {
        System.out.println("Informe os dados do usuario");
        String id = String.valueOf(ID);
        System.out.print("Digite nome : ");
        String nome = scanner.nextLine();// ler titulo
        System.out.print("Digite CPF : ");
        String cpf = scanner.nextLine();

        servicoLivros.Editar(id, nome, cpf);
    }

}