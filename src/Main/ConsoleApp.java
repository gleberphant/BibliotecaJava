package Main;

import Infraestrutura.Console.ConsoleUI;
import Infraestrutura.Console.ConsoleConfig;


public class ConsoleApp {
    public static void main(String[] args) {

        // Inicia a UI (Infraestrutura de Saída)
        ConsoleUI uiConsole = new ConsoleUI( ConsoleConfig.configurarRoteadorConsole());
        uiConsole.executar();
    }
}
