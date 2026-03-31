package Main;

import java.util.Scanner;


import Adaptadores.ControladoresConsole.ControladorHistorico;
import Adaptadores.ControladoresConsole.ControladorLivro;
import Adaptadores.ControladoresConsole.ControladorRecomendacoes;
import Adaptadores.ControladoresConsole.ControladorUsuario;

import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;
import Infraestrutura.Roteadores.RoteadorConsole;
import Infraestrutura.UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        // Inicia a UI (Infraestrutura de Saída)
        ConsoleUI uiConsole = new ConsoleUI(configurarRoteadorConsole());
        uiConsole.executar();
    }

    public static RoteadorConsole configurarRoteadorConsole() {

        // configurar entrada de dados
        var entrada = new Scanner(System.in);

        // configurar servicos
        var servicoLivros = new ServicoLivros();
        var servicoUsuarios = new ServicoUsuarios();

        // configurar controladores
        var controleLivros = new ControladorLivro(servicoLivros,servicoUsuarios, entrada);
        var controleUsuarios = new ControladorUsuario(servicoUsuarios, entrada);
        var controleHistorico = new ControladorHistorico(servicoUsuarios, entrada);
        var controleRecomendacoes = new ControladorRecomendacoes(servicoLivros,servicoUsuarios, entrada);

        // Configuração do Menus da UI
        // Configuração do SubMenu Usuarios
        RoteadorConsole menuUsuario = new RoteadorConsole("Gestão de Usuários")
                .adicionarRota(1, "Adicionar Usuários", controleUsuarios::Adicionar)
                .adicionarRota(2, "Visualizar Usuários", controleUsuarios::Visualizar)
                .adicionarRota(3, "Editar Usuários", controleUsuarios::Editar)
                .adicionarRota(4, "Listar Usuários", controleUsuarios::Listar)
                .adicionarRota(5, "Remover Usuários", controleUsuarios::Remover)
                .adicionarRota(0, "Voltar", null);

        // Configuração do SubMenu Livros
        RoteadorConsole menuLivros = new RoteadorConsole("Gestão de Livros")
                .adicionarRota(1, "Adicionar Livro", controleLivros::Adicionar)
                .adicionarRota(2, "Visualizar Livros", controleLivros::Visualizar)
                .adicionarRota(3, "Editar Livros", controleLivros::Editar)
                .adicionarRota(4, "Listar Livros", controleLivros::Listar)
                .adicionarRota(5, "Remover Livros", controleLivros::Remover)
                .adicionarRota(0, "Voltar", null);

        // Configuração do SubMenu Espera
        RoteadorConsole menuEspera = new RoteadorConsole("Empréstimo de Livros")
                .adicionarRota(1, "Emprestar um Livro", controleLivros::Emprestar)
                .adicionarRota(2, "Devolver Livro", controleLivros::Devolver)
                .adicionarRota(3, "Visualizar Empréstimos", controleLivros::VisualizarEmprestimos)
                .adicionarRota(4, "Listar Todos Empréstimos", controleLivros::ListarEmprestimos)
                .adicionarRota(0, "Voltar", null);

        // Configuração do SubMenu Historico
        RoteadorConsole menuHistorico = new RoteadorConsole("Gestão de Historico")
                .adicionarRota(1, "Visualizar Histórico", controleHistorico::Listar)
                .adicionarRota(0, "Voltar", null);

        // Configuração do SubMenu Recomendacao
        RoteadorConsole menuRecomendacao = new RoteadorConsole("Gestão de Recomendacao")
                .adicionarRota(1, "Adicionar Recomendacao", controleRecomendacoes::Adicionar)
                .adicionarRota(2, "Listar Recomendacoes", controleRecomendacoes::Listar)
                .adicionarRota(0, "Voltar", null);

        // Configuração do Menu Principal
        return new RoteadorConsole("Sistema Biblioteca")
                .adicionarSubRoteador(1, "Menu Usuarios", menuUsuario)
                .adicionarSubRoteador(2, "Menu Livros", menuLivros)
                .adicionarSubRoteador(3, "Menu Empréstimos", menuEspera)
                .adicionarSubRoteador(4, "Menu Historico", menuHistorico)
                .adicionarSubRoteador(5, "Menu Recomendacoes", menuRecomendacao)
                .adicionarRota(0, "Sair", null);
    }
}