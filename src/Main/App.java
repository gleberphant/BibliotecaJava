package Main;

import ConsoleUI.ConsoleConfigRoteador;
import ConsoleUI.ConsoleMain;


public class App {
    public static void main(String[] args) {

        // Inicia a UI (Infraestrutura de Saída)
        ConsoleMain uiConsole = new ConsoleMain( ConsoleConfigRoteador.configurarRoteadorConsole());
        uiConsole.executar();
    }
}
