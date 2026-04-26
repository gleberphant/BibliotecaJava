package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Usuario;

public class ControleUsuario {
    private final ServicoUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControleUsuario(ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        servicoUsuarios.Adicionar(setUsuario(0));

    }

    public void Editar() {

        Usuario usuario = BuscarUsuarioID();

        if (usuario == null)
            return;

        servicoUsuarios.Editar(setUsuario(usuario.ID));

    }

    public void Visualizar() {

        Usuario usuario = BuscarUsuarioID();

        if (usuario == null)
            return;

        System.out.println(exibeUsuario(usuario));

    }

    public void Listar() {

        System.out.println("Listando todos os usuarios");

        for (Usuario usuario : servicoUsuarios.Listar()) {
            System.out.println(exibeUsuario(usuario));
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do usuario para remover");

        try {
            servicoUsuarios.Remover(scanner.nextLine());
        } catch (Exception e) {
            System.out.printf("Algo deu errado  : %s", e.getMessage());
            return;
        }

        System.out.println("Usuario removido com sucesso.");

    }

    public void FazerLogin() {

        System.out.printf("\nInforme o login");
        String login = scanner.nextLine();

        System.out.printf("\nInforme a senha");
        String senha = scanner.nextLine();

        try {
            servicoUsuarios.Login(login, senha);
        } catch (Exception e) {
            System.out.printf("Algo deu errado  : $s", e.getMessage());
            return;
        }

        System.out.printf("\nUsuario Logado: %s", servicoUsuarios.GetUsuarioLogado());

    }

    public void VisualizarHistorico() {
        Usuario usuario = servicoUsuarios.GetUsuarioLogado();

        if (usuario == null)
            return;

        System.out.println(exibirHistorico(usuario));

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

    private Usuario BuscarUsuarioID() {
        return BuscarUsuarioID(this.servicoUsuarios, this.scanner);
    }

    private Usuario setUsuario(int id) {

        System.out.println("Informe os dados do usuario");
        System.out.print("Nome : ");
        String nome = scanner.nextLine();// ler titulo
        System.out.print("CPF : ");
        String cpf = scanner.nextLine();
        System.out.print("Senha : ");
        String senha = scanner.nextLine();

        return new Usuario(id, nome, cpf, senha);
    }

    private String exibirHistorico(Usuario usuario) {

        StringBuilder sb = new StringBuilder();

        sb.append("\n ----------------- Histórico de visualizações --------------- \n");
        sb.append(String.format("\n| Usuario: %-50s| \n", usuario.Nome));
        sb.append("\n ------------------------------------------------------------ \n");
        for (var livro : usuario.historicoNavegacao) {
            sb.append(
                    String.format("|Título: %-22s Autor: %22s|\n",
                            livro.Titulo,
                            livro.Autor));
        }
        sb.append("\n ------------------------------------------------------------ \n");
        return sb.toString();
    }

    private String exibeUsuario(Usuario usuario) {

        return String.format(
                """
                         ------------------------------------------------------------
                        | ID         : %-45s |
                        | Nome       : %-45s |
                        | CPF        : %-45s |
                        | Senha      : %-45s |
                         ------------------------------------------------------------
                        """,
                usuario.ID,
                usuario.Nome,
                usuario.CPF,
                usuario.Senha);
    }
}