package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.Modelos.*;

public class ControleUsuario {
    private final ServicoUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControleUsuario(ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        setUsuario(servicoUsuarios.Adicionar(new Usuario()));

    }

    public void FazerLogin() {

        System.out.printf("\nInforme o login");
        String login = scanner.nextLine();

        System.out.printf("\nInforme a senha");
        String senha = scanner.nextLine();

        servicoUsuarios.Login(login, senha);

        System.out.printf("\nUsuario Logado: %s", servicoUsuarios.GetUsuarioLogado());

    }

    public void Editar() {

        Usuario usuario = BuscarUsuarioID();

        if (usuario == null)
            return;

        setUsuario(usuario.ID);

    }

    public void Visualizar() {

        Usuario usuario = BuscarUsuarioID();

        if (usuario == null)
            return;

        System.out.println(exibeUsuario(usuario));
    }

    public void VisualizarHistorico() {
        Usuario usuario = BuscarUsuarioID();

        if (usuario == null)
            return;

        System.out.println(exibeUsuario(usuario));
        System.out.println(exibirHistorico(usuario));

    }

    private String exibirHistorico(Usuario usuario) {

        var historico = usuario.historicoNavegacao;

        StringBuilder sb = new StringBuilder();

        sb.append("\nHistórico de visualizações\n");
        for (var livro : historico) {
            sb.append(
                    String.format("|            > %-40s |\n", livro.Titulo));
        }

        return sb.toString();
    }

    public void Listar() {

        System.out.println("Listando todos os usuarios");
        Usuario[] listUsuarios = servicoUsuarios.Listar();

        for (Usuario usuario : listUsuarios) {
            System.out.println(exibeUsuario(usuario));
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do usuario para remover");

        servicoUsuarios.Remover(scanner.nextLine());

        System.out.println("Removendo o usuario ");

    }

    private Usuario BuscarUsuarioID() {
        return BuscarUsuarioID(this.servicoUsuarios, this.scanner);
    }

    // metodo static para outros controladores
    public static Usuario BuscarUsuarioID(ServicoUsuarios usuarios, Scanner scanner) {
        System.out.println("Informe o ID do usuario para pesquisar");

        try {
            Usuario usuario = usuarios.Visualizar(scanner.nextLine());
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

        servicoUsuarios.Editar(id, nome, cpf);
    }

    private String exibeUsuario(Usuario usuario) {

        int largura = 55;

        return String.format(
                """
                         %s
                        | ID         : %-40s |
                        | Nome       : %-40s |
                        | Senha      : %-40s |
                        %s %s
                        """,
                "-".repeat(largura),
                usuario.ID,
                usuario.Nome,
                usuario.Senha,
                "",
                "-".repeat(largura));
    }
}