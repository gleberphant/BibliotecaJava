package Infraestrutura.RoteadoresConsole;

import java.util.Map;

import Aplicacao.Interfaces.IRoteador;
import Aplicacao.Interfaces.IServico;

public class RoteadorMain implements IRoteador {

    IServico servico;
    String Titulo;

    Map<Integer, String> menu;

    public RoteadorMain() {
    }

    public RoteadorMain(IServico servico) {
        this.servico = servico;
        this.Titulo = "main";
        this.menu = Map.of(
                1, " 1 - Usuarios",
                2, " 2 - Livros",
                3, " 3 - Fila de Espera",
                4, " 4 - Historico Navegação",
                5, " 5 - Recomendações",
                0, " 0 - Sair");

    }

    public IRoteador Roteamento(int select) {
        switch (select) {
            case 1 -> {
                return new RoteadorLivro(servico);
            }
            case 2 -> {
                return new RoteadorLivro(servico);
            }
            case 3 -> {
                return new RoteadorLivro(servico);
            } // Corrigido: você estava chamando ListarLivros aqui
            case 4 -> {
                return new RoteadorLivro(servico);
            }
            case 5 -> {
                return new RoteadorLivro(servico);
            }
            case 0 -> {
                return null;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }

        return this; // Retorna true para manter o loop ativo
    }

    public Map<Integer, String> GetMenu() {
        return menu;
    }

    public String GetTitulo() {
        return Titulo;
    }

    public void SetMenu(Map<Integer, String> menu) {
        this.menu = menu;

    }

    public void Sair() {
        System.out.println(".       Finalizando      .");
        return;
    }

}