package Testes;

import Handlers.*;
import Models.Livro;
import Repositories.*;
import Usecases.*;

public class TesteServicoLivro {

    public static void main(String[] args) {

        System.out.println("REALIZANDO TESTES DAS FUNCIONALDIADES DA APLICAÇÃO");
        // configura aplicação
        HandlerCLI app = new HandlerCLI(new ServicoLivro(new RepositorioLivro()));

        int numItens = 5;

        // mocar dados em massa
        System.out.println("\nMockar Dados");
        for (int i = 0; i < numItens; i++)
            app.servico.AdicionarLivro(new Livro("Livro " + i, "Autor " + i, i));

        // visualizar
        System.out.println("\nVisualizar Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println(app.servico.VisualizarLivro(i));
        }

        // remover
        System.out.println("\nRemover Dados");
        for (int i = 0; i < numItens - 2; i++) {
            app.servico.RemoverLivro(i);
        }

        // listar
        System.out.println("\nListar Dados");
        app.ListarLivros();

    }

}
