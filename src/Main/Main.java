package Main;

import Aplicacao.Interfaces.IComando;
import Infraestrutura.Config.ConfigConsoleMenu;
import Infraestrutura.UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        // Configura a árvore de Menus (Composição)
        IComando menuRaiz = ConfigConsoleMenu.configurar();

        // Inicia a UI (Infraestrutura de Saída)
        ConsoleUI uiConsole = new ConsoleUI(menuRaiz);
        uiConsole.executar();
    }
}