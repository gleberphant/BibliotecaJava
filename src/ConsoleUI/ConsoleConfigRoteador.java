package ConsoleUI;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.ControleEmprestimos;
import Adaptadores.ControladoresConsole.ControleLivro;
import Adaptadores.ControladoresConsole.ControleRecomendacoes;
import Adaptadores.ControladoresConsole.ControleUsuario;
import Adaptadores.ExibicaoConsole.ExibicaoConsole;
import Adaptadores.Repositorios.EmMemoria.RepositorioLivros;

import Adaptadores.Repositorios.EmMemoria.RepositorioUsuariosLista;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;
import Aplicacao.Servicos.ServicoEmprestimos;
import Aplicacao.Servicos.ServicoLivros;
import Aplicacao.Servicos.ServicoUsuarios;

public class ConsoleConfigRoteador {

        public static void MockarDados(RepositorioLivros repositorioLivros, RepositorioUsuariosLista repositorioUsuarios) {
                // mockar dados

                int numLivros   = (int) (Math.random() * 100);
                int numUsuarios = 10;

                System.out.println("\n::: Mockando Livros :::");

                for (int i = 0; i <= numLivros; i++) {
                        var id = ((int) (Math.random() * 100));
                        repositorioLivros.InserirLivro(new Livro("" + id, "Livro " + id, "Autor " + id, ""));
                        System.out.println(repositorioLivros.BuscarLivroPorID(id+""));
                }

                System.out.println("\n::: Inserir conexoes entre os livros :::");
                for (int i = 0; i < numLivros; i++) {

                        // servicoLivros.InserirRecomendacao(servicoLivros.BuscarID(i + ""),
                        // servicoLivros.BuscarID((numItens - i) + ""));

                        servicoLivros.VisualizarLivro(servicoUsuarios.GetUsuarioLogado(), i + "");
                        System.out.println(servicoLivros.ListarRecomendacoes(i + ""));
                }


                                System.out.println("\n::: Mockando Usuarios :::");
                for (int i = 1; i <= numUsuarios; i++) {

                        repositorioUsuarios.Inserir(new Usuario(i, "Nome " + i, "cpf" + i, "senha"));
                        System.out.println(repositorioUsuarios.BuscarID(i));
                }

        }

        // configura menus e injeta dependências
        public static ConsoleRoteador configurarRoteadorConsole() {

                // configurar entrada de dados
                var entrada = new Scanner(System.in);
                var exibicao = new ExibicaoConsole();
                // criar repositorios

                var respositorioLivros = new RepositorioLivros();
                var respositorioUsuarios = new RepositorioUsuariosLista();

                // configurar servicos
                var servicoLivros = new ServicoLivros(respositorioLivros);
                var servicoUsuarios = new ServicoUsuarios(respositorioUsuarios);
                var servicoEmprestimos = new ServicoEmprestimos(respositorioLivros);

                // inserir usuario ADM
                servicoUsuarios.Adicionar(new Usuario(0, "root", "0123456789", "root"));
                servicoUsuarios.Login("root", "root");

                // mockar dados
                MockarDados(servicoLivros, servicoUsuarios);

                // configurar controladores
                var controleUsuarios = new ControleUsuario(servicoUsuarios, entrada, exibicao);
                var controleLivros = new ControleLivro(servicoLivros, servicoUsuarios, entrada, exibicao);
                var controleEmprestimos = new ControleEmprestimos(servicoLivros, servicoUsuarios, servicoEmprestimos,
                                entrada, exibicao);
                var controleRecomendacoes = new ControleRecomendacoes(servicoLivros, servicoUsuarios, entrada,
                                exibicao);

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
                                .adicionarRota(1, "Adicionar Livro", controleLivros::AdicionarLivro)
                                .adicionarRota(2, "Visualizar Livros", controleLivros::VisualizarLivro)
                                .adicionarRota(3, "Editar Livros", controleLivros::EditarLivro)
                                .adicionarRota(4, "Listar Livros", controleLivros::ListarLivros)
                                .adicionarRota(5, "Remover Livros", controleLivros::RemoverLivro)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do SubMenu Espera
                ConsoleRoteador menuEmprestimos = new ConsoleRoteador("Empréstimo de Livros")
                                .adicionarRota(1, "Emprestar um Livro", controleEmprestimos::Emprestar)
                                .adicionarRota(2, "Devolver Livro", controleEmprestimos::Devolver)
                                .adicionarRota(3, "Visualizar Empréstimos", controleEmprestimos::VisualizarEmprestimos)
                                .adicionarRota(4, "Listar Todos Empréstimos", controleEmprestimos::ListarEmprestimos)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do SubMenu Recomendacao
                ConsoleRoteador menuRecomendacao = new ConsoleRoteador("Gestão de Recomendacao")
                                .adicionarRota(1, "Visualizar Recomendacoes",
                                                controleRecomendacoes::VisualizarRecomendacoes)
                                .adicionarRota(2, "Listar Todas Recomendacoes",
                                                controleRecomendacoes::ListarRecomendacoes)
                                .adicionarRota(3, "Explorar Livros",
                                                controleRecomendacoes::BuscarCaminho)
                                .adicionarRota(0, "Voltar", null);

                // Configuração do Menu Principal
                ConsoleRoteador menuRaiz = new ConsoleRoteador("Sistema Biblioteca")
                                .adicionarSubRoteador(1, "Menu Usuarios", menuUsuario)
                                .adicionarSubRoteador(2, "Menu Livros", menuLivros)
                                .adicionarSubRoteador(3, "Menu Empréstimos", menuEmprestimos)
                                .adicionarRota(4, "Visualizar Histórico", controleUsuarios::VisualizarHistorico)
                                .adicionarSubRoteador(5, "Menu Recomendacoes", menuRecomendacao)
                                .adicionarRota(9, "Fazer Login", controleUsuarios::FazerLogin)
                                .adicionarRota(0, "Sair", null);

                menuRaiz.SetLogin(() -> servicoUsuarios.GetUsuarioLogado());

                return menuRaiz;
        }

}
