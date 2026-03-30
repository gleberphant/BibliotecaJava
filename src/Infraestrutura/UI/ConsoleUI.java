package Infraestrutura.UI;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import Infraestrutura.Roteadores.IComando;

public class ConsoleUI {
    private final Scanner scanner;
    private final Stack<IComando> historico;

    public ConsoleUI(IComando menuRaiz) {
        this.scanner = menuRaiz.GetScanner();
        this.historico = new Stack<>();
        this.historico.push(menuRaiz);
    }

    public void executar() {
        while (!historico.isEmpty()) {
            IComando atual = historico.peek();
            exibirMenu(atual);
            processarEntrada(atual);
        }
        System.out.println("Sistema finalizado.");
        scanner.close();
    }

    private void exibirMenu(IComando menu) {
        String titulo = menu.GetTitulo();
        Map<Integer, String> descricao = menu.GetMenu();
        String linha = "-".repeat(50);

        System.out.printf("\n%s\n", linha);
        System.out.printf("| %-46s |\n", titulo);
        System.out.printf("%s\n", linha);

        descricao.forEach((key, value) -> {
            System.out.printf("| [ %s ]  %-39s |\n", key, value);
        });

        System.out.printf("%s\n", linha);
        System.out.print("Escolha uma opção: ");
    }

    private void processarEntrada(IComando atual) {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            IComando proximo = atual.Roteamento(opcao);

            if (proximo == null) {
                historico.pop();
            } else if (proximo != atual) {
                historico.push(proximo);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite um número válido.");
        }
    }
}