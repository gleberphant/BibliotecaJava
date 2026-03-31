package Testes;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.*;
import Adaptadores.Repositorios.PersistenciaEmMemoria;
import Aplicacao.CasosDeUso.ServicoLivros;

import Dominio.Modelos.Livro;

public class TesteServicoLivro {

    public static void main(String[] args) {

        System.out.println("REALIZANDO TESTES DAS FUNCIONALDIADES DA APLICAÇÃO");
        // configura aplicação
        ControladorLivro app = new ControladorLivro(
                new ServicoLivros(new PersistenciaEmMemoria()),
                new Scanner(System.in));

        int numItens = 5;

        // mocar dados em massa
        System.out.println("\nMockando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println("Livro " + i + "Autor " + i);
            app.servico.Adicionar(new Livro("Livro " + i, "Autor " + i, i));
        }

        // visualizar
        System.out.println("\nVisualizando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println(app.servico.Visualizar(i));
        }

        // remover
        System.out.println("\nRemovendo Dados");
        for (int i = 0; i < numItens - 2; i++) {

            app.servico.Remover(i);
        }

        // listar
        System.out.println("\nListar Dados");
        app.Listar();

    }

}
