package Testes;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.*;
import Adaptadores.Repositorios.EmMemoria.RepositorioLivrosGrafo;

import Adaptadores.Repositorios.EmMemoria.RepositorioUsuariosLista;
import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class TesteServicoLivro {

    public static void main(String[] args) {

        System.out.println("REALIZANDO TESTES DO SERVICO LIVRO");
        // configura aplicação

        var servicoLivros = new ServicoLivros(new RepositorioLivrosGrafo());
        var servicoUsuarios = new ServicoUsuarios(new RepositorioUsuariosLista());
        servicoUsuarios.Adicionar(new Usuario(0, "root","","senha"));
        var root = servicoUsuarios.Login("root", "senha");

        int numItens = 5;

        // mocar dados em massa
        System.out.println("\nMockando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println("Livro " + i + "Autor " + i);
            servicoLivros.Adicionar(new Livro(i, "Livro " + i, "Autor " + i, ""));
        }

        // visualizar
        System.out.println("\nVisualizando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println(servicoLivros.Visualizar(root, i+""));
        }

        // remover
        System.out.println("\nRemovendo Dados");
        for (int i = 0; i < numItens - 2; i++) {

            servicoLivros.Remover(servicoLivros.BuscarID(i+""));
        }

        // listar
        ControleLivro app = new ControleLivro(
                servicoLivros,
                servicoUsuarios,
                new Scanner(System.in));

        System.out.println("\nListar Dados");
        app.Listar();

    }

}
