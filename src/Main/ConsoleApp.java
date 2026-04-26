package Main;

import Infraestrutura.Console.ConsoleUI;
import Infraestrutura.Console.ConfigRoteador;


public class ConsoleApp {
    public static void main(String[] args) {

        // Inicia a UI (Infraestrutura de Saída)
        ConsoleUI uiConsole = new ConsoleUI( ConfigRoteador.configurarRoteadorConsole());
        uiConsole.executar();
    }
}
