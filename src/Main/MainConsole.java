package Main;

import java.util.Map;
import java.util.Scanner;

import Adaptadores.Repositorios.PersistenciaEmMemoria;
import Aplicacao.CasosDeUso.ServicoBiblioteca;
import Aplicacao.Interfaces.IRoteador;
import Infraestrutura.RoteadoresConsole.RoteadorMain;

public class MainConsole {

    public static void main(String[] args) {

        // configura aplicação
        RoteadorMain app = new RoteadorMain(new ServicoBiblioteca(new PersistenciaEmMemoria()));

        // mocar dados
        // Livro[] livrosMocados = {
        // new Livro("Livro 1", "Autor 1", 1),
        // new Livro("Livro 2", "Autor 2", 2),
        // new Livro("Livro 3", "Autor 3", 3),
        // };

        // roda aplicação
        Scanner sc = new Scanner(System.in);
        String select, paginaAtual;
        IRoteador roteadorAtual = app;
        Map<Integer, String> menu;
        while (true) {

            menu = roteadorAtual.GetMenu();
            paginaAtual = roteadorAtual.GetTitulo();

            System.out.printf("---------------------------------------- \n");
            System.out.printf("| %-36s |\n", paginaAtual);
            for (Integer key : menu.keySet()) {
                System.out.printf("| %-36s |\n", menu.get(key));

            }
            System.out.println("----------------------------------------");

            System.out.println("Escolha uma opção: ");

            select = sc.nextLine();

            try {
                roteadorAtual = roteadorAtual.Roteamento(Integer.parseInt(select));

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido: ");
            }

            if (roteadorAtual == null)
                if (paginaAtual == "main")
                    break;
                else
                    roteadorAtual = app;

        }

        sc.close();

    }

}
