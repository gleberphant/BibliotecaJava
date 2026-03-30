package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.IServico;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ControladorEspera implements IControlador {
    public IServico<Livro> servicoLivros;
    public IServico<Usuario> servicoUsuarios;
    private final Scanner scanner;

    public ControladorEspera(IServico<Livro> servicoLivros, IServico<Usuario> servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servicoLivros = servicoLivros;
        this.servicoUsuarios = servicoUsuarios;
    }

    public void Adicionar() {

        System.out.println("Informe o ID do LIVRO para procurar");

        int idLivro = Integer.parseInt(scanner.nextLine()); // ler dados

        Livro livro = servicoLivros.Visualizar(idLivro);

        System.out.println(livro.toString());

        System.out.println("Informe o ID do USUARIO que ira entrar na lista de espera");

        int idUsuario = Integer.parseInt(scanner.nextLine()); // ler dados

        Usuario usuario = servicoUsuarios.Visualizar(idUsuario);

        System.out.println(usuario.toString());

        System.out.println("Inserindo usuario na fila de espera do livro");

        livro.FilaEspera.Inserir(usuario);

        servicoLivros.Editar(livro);

    }

    public void Visualizar() {
        System.out.println("Informe o ID do LIVRO para procurar");

        int idLivro = Integer.parseInt(scanner.nextLine()); // ler dados

        Livro livro = servicoLivros.Visualizar(idLivro);
        System.out.println(livro.toString());

        System.out.print("\n Usuarios na fila > ");

        for (var usuario : livro.FilaEspera) {
            System.out.print(" [" + usuario.toString() + "] ");
        }

        System.out.print("\n");
    }

    public void Editar() {
    }

    public void Listar() {

        for (var livro : servicoLivros.Listar()) {

            System.out.print("\n Livro " + livro.toString() + " FILA > ");

            for (var usuario : livro.FilaEspera) {
                System.out.print(" [" + usuario.toString() + "] ");
            }

        }

        System.out.print("\n");
    }

    public void Remover() {
    }
}