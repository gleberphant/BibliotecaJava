package Main;


import Adaptadores.Repositorios.PersistenciaEmMemoria;
import Aplicacao.CasosDeUso.ServicoLivro;
import Aplicacao.Interfaces.IComando;
import Infraestrutura.Config.ConfigConsoleMenu;
import Infraestrutura.UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        // 1. Configura Infra/Domínio
        var servico = new ServicoLivro(new PersistenciaEmMemoria());

        // 2. Configura a árvore de Menus (Composição)
        IComando menuRaiz = ConfigConsoleMenu.configurar(servico);

        // 3. Inicia a UI (Infraestrutura de Saída)
        ConsoleUI uiConsole = new ConsoleUI(menuRaiz);
        uiConsole.executar();
    }
}