package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ControladorUsuario implements IControlador {
    public ServicoUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControladorUsuario(ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        setUsuario(servicoUsuarios.Adicionar(new Usuario()));

    }

    public void Editar() {

        Usuario usuario = getUsuario();

        if (usuario == null)
            return;

        setUsuario(usuario.ID);

    }

    public void Visualizar() {

        Usuario usuario = getUsuario();

        if (usuario == null)
            return;

        System.out.println(usuario.toString());
    }

    public void Listar() {

        System.out.println("Listando todos os usuarios");
        Usuario[] listUsuarios = servicoUsuarios.Listar();

        for (Usuario usuario : listUsuarios) {
            System.out.println(usuario.toString());
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do usuario para remover");

        servicoUsuarios.Remover(scanner.nextLine());

        System.out.println("Removendo o usuario ");

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

        servicoUsuarios.Editar(id, nome, cpf);
    }

}