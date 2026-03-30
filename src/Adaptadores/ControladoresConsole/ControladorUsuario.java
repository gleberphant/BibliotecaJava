package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;

import Dominio.Modelos.Usuario;

public class ControladorUsuario implements IControlador {
    public IServico<Usuario> servico;
    private final Scanner scanner;

    public ControladorUsuario(IServico<Usuario> servico, Scanner scanner) {
        this.scanner = scanner;
        this.servico = servico;
    }

    public void Adicionar() {

        System.out.println("Informe os dados do USUÁRIO");

        System.out.print("Digite nome do Usuario ");
        String nome = scanner.nextLine();
        servico.Adicionar(new Usuario(nome));

    }

    public void Editar() {

        System.out.println("Informe o ID do USUARIO para editar");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        Usuario usuarioEditado = servico.Visualizar(id);

        System.out.println("Informe os novos dados do USUÁRIO");

        usuarioEditado.Nome = scanner.nextLine();

        servico.Editar(usuarioEditado);

    }

    public void Visualizar() {

        System.out.println("Informe o ID do USUÁRIO para pesquisar");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        Usuario usuario = servico.Visualizar(id);

        System.out.println("Visualizando o USUÁRIO > " + id);
        System.out.println(usuario);
    }

    public void Listar() {

        System.out.println("Listando todos os usuarios");
        Usuario[] listUsuarios = servico.Listar();

        for (Usuario usuario : listUsuarios) {
            System.out.println(" ** " + usuario.toString() + " ");
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do usuario para remover");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        servico.Remover(id);

        System.out.println("Removendo o usuario > " + id);

    }

}