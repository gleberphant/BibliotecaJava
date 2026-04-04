package Testes;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.*;
import Adaptadores.Repositorios.EmMemoria.RepositorioLivrosGrafo;
import Adaptadores.Repositorios.EmMemoria.RepositorioLivrosLista;
import Adaptadores.Repositorios.EmMemoria.RepositorioUsuarios;
import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.Modelos.Livro;

public class TesteServicoLivro {

    public static void main(String[] args) {

        System.out.println("REALIZANDO TESTES DAS FUNCIONALDIADES DA APLICAÇÃO");
        // configura aplicação

        var servicoLivros = new ServicoLivros(new RepositorioLivrosGrafo());
        var servicoUsuarios = new ServicoUsuarios(new RepositorioUsuarios());

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
            System.out.println(servicoLivros.Visualizar(i));
        }

        // remover
        System.out.println("\nRemovendo Dados");
        for (int i = 0; i < numItens - 2; i++) {

            servicoLivros.Remover(i);
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
