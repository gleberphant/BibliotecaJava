package Infraestrutura.Console;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.ControleLivro;
import Adaptadores.ControladoresConsole.ControleRecomendacoes;
import Adaptadores.ControladoresConsole.ControleUsuario;
import Adaptadores.Repositorios.EmMemoria.RepositorioLivros;
import Adaptadores.Repositorios.EmMemoria.RepositorioUsuarios;
import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ConsoleConfig {

        public static void MockarDados(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios) {
                // mockar dados
                int numItens = 5;
                System.out.println("\n::: Mockando Livros :::");
                for (int i = 0; i < numItens; i++) {
                        servicoLivros.Adicionar(new Livro(i, "Livro" + i, "Autor" + i, ""));
                        System.out.println(servicoLivros.Visualizar(i + ""));
                }

                System.out.println("\n::: Mockando Usuarios :::");
                for (int i = 1; i < numItens; i++) {

                        servicoUsuarios.Adicionar(new Usuario(i, "Nome" + i, "senha" + i));
                        System.out.println(servicoUsuarios.Visualizar(i + ""));
                }

        }

        public static ConsoleRoteador configurarRoteadorConsole() {

                // configurar entrada de dados
                var entrada = new Scanner(System.in);

                // configurar servicos
                var servicoLivros = new ServicoLivros(new RepositorioLivros());
                var servicoUsuarios = new ServicoUsuarios(new RepositorioUsuarios());

                // inserir usuario ADM
                servicoUsuarios.Adicionar(new Usuario(0, "root", "root"));
                servicoUsuarios.Login("root", "root");

                // mockar dados
                MockarDados(servicoLivros, servicoUsuarios);

                // configurar controladores
                var controleUsuarios = new ControleUsuario(servicoUsuarios, entrada);
                var controleLivros = new ControleLivro(servicoLivros, servicoUsuarios, entrada);
                var controleRecomendacoes = new ControleRecomendacoes(servicoLivros, servicoUsuarios, entrada);

                // Configuração do Menus da UI
                // Configuração do SubMenu Usuarios
                ConsoleRoteador menuUsuario = new ConsoleRoteador("Gestão de Usuários")
                                .adicionarRota(1, "Adicionar Usuários", controleUsuarios::Adicionar)
                                .adicionarRota(2, "Visualizar Usuários", controleUsuarios::Visualizar)
                                .adicionarRota(3, "Editar Usuários", controleUsuarios::Editar)
                                .adicionarRota(4, "Listar Usuários", controleUsuarios::Listar)
                                .adicionarRota(5, "Remover Usuários", controleUsuarios::Remover)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do SubMenu Livros
                ConsoleRoteador menuLivros = new ConsoleRoteador("Gestão de Livros")
                                .adicionarRota(1, "Adicionar Livro", controleLivros::Adicionar)
                                .adicionarRota(2, "Visualizar Livros", controleLivros::Visualizar)
                                .adicionarRota(3, "Editar Livros", controleLivros::Editar)
                                .adicionarRota(4, "Listar Livros", controleLivros::Listar)
                                .adicionarRota(5, "Remover Livros", controleLivros::Remover)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do SubMenu Espera
                ConsoleRoteador menuEspera = new ConsoleRoteador("Empréstimo de Livros")
                                .adicionarRota(1, "Emprestar um Livro", controleLivros::Emprestar)
                                .adicionarRota(2, "Devolver Livro", controleLivros::Devolver)
                                .adicionarRota(3, "Visualizar Empréstimos", controleLivros::VisualizarEmprestimos)
                                .adicionarRota(4, "Listar Todos Empréstimos", controleLivros::ListarEmprestimos)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do SubMenu Recomendacao
                ConsoleRoteador menuRecomendacao = new ConsoleRoteador("Gestão de Recomendacao")
                                .adicionarRota(1, "Adicionar Recomendacao", controleRecomendacoes::Adicionar)
                                .adicionarRota(2, "Listar Recomendacoes", controleRecomendacoes::Listar)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do Menu Principal
                ConsoleRoteador menuRaiz = new ConsoleRoteador("Sistema Biblioteca")
                                .adicionarSubRoteador(1, "Menu Usuarios", menuUsuario)
                                .adicionarSubRoteador(2, "Menu Livros", menuLivros)
                                .adicionarSubRoteador(3, "Menu Empréstimos", menuEspera)
                                .adicionarRota(4, "Visualizar Histórico", controleUsuarios::VisualizarHistorico)
                                .adicionarSubRoteador(5, "Menu Recomendacoes", menuRecomendacao)
                                .adicionarRota(9, "Fazer Login", controleUsuarios::FazerLogin)
                                .adicionarRota(0, "Sair", null);

                menuRaiz.SetLogin(() -> servicoUsuarios.GetUsuarioLogado());

                return menuRaiz;
        }

}
