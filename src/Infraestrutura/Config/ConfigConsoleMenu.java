package Infraestrutura.Config;

import Adaptadores.ControladoresConsole.ControladorEspera;
import Adaptadores.ControladoresConsole.ControladorHistorico;
import Adaptadores.ControladoresConsole.ControladorLivro;
import Adaptadores.ControladoresConsole.ControladorRecomendacoes;
import Adaptadores.ControladoresConsole.ControladorUsuario;
import Aplicacao.CasosDeUso.ServicoBiblioteca;
import Aplicacao.Interfaces.IComando;
import Infraestrutura.Roteadores.RoteadorConsole;

public class ConfigConsoleMenu {
        public static IComando configurar(ServicoBiblioteca servico) {

                var controleLivros = new ControladorLivro(servico);
                var controleUsuarios = new ControladorUsuario(servico);
                var controleEspera = new ControladorEspera(servico);
                var controleHistorico = new ControladorHistorico(servico);
                var controleRecomendacoes = new ControladorRecomendacoes(servico);

                // Configuração do SubMenu Usuarios
                IComando menuUsuario = new RoteadorConsole("Gestão de Usuários")
                                .adicionarOpcao(1, "Adicionar Usuários", controleUsuarios::Adicionar)
                                .adicionarOpcao(2, "Visualizar Usuários", controleUsuarios::Visualizar)
                                .adicionarOpcao(3, "Editar Usuários", controleUsuarios::Editar)
                                .adicionarOpcao(4, "Listar Usuários", controleUsuarios::Listar)
                                .adicionarOpcao(5, "Remover Usuários", controleUsuarios::Remover)
                                .adicionarOpcao(0, "Voltar", null);

                // Configuração do SubMenu Livros
                IComando menuLivros = new RoteadorConsole("Gestão de Livros")
                                .adicionarOpcao(1, "Adicionar Livro", controleLivros::Adicionar)
                                .adicionarOpcao(2, "Visualizar Livros", controleLivros::Visualizar)
                                .adicionarOpcao(3, "Editar Livros", controleLivros::Editar)
                                .adicionarOpcao(4, "Listar Livros", controleLivros::Listar)
                                .adicionarOpcao(5, "Remover Livros", controleLivros::Remover)
                                .adicionarOpcao(0, "Voltar", null);

                // Configuração do SubMenu Espera
                IComando menuEspera = new RoteadorConsole("Gestão de Espera")
                                .adicionarOpcao(1, "Adicionar Usuario a lista de espera", controleEspera::Adicionar)
                                .adicionarOpcao(2, "Visualizar Listas", controleEspera::Listar)
                                .adicionarOpcao(0, "Voltar", null);

                // Configuração do SubMenu Historico
                IComando menuHistorico = new RoteadorConsole("Gestão de Historico")
                                .adicionarOpcao(1, "Visualizar Histórico", controleHistorico::Listar)
                                .adicionarOpcao(0, "Voltar", null);

                // Configuração do SubMenu Recomendacao
                IComando menuRecomendacao = new RoteadorConsole("Gestão de Recomendacao")
                                .adicionarOpcao(1, "Adicionar Recomendacao", controleRecomendacoes::Adicionar)
                                .adicionarOpcao(2, "Listar Recomendacoes", controleRecomendacoes::Listar)
                                .adicionarOpcao(0, "Voltar", null);

                // Configuração do Menu Principal
                return new RoteadorConsole("Sistema Biblioteca")
                                .adicionarSubMenu(1, "Menu Usuarios", menuUsuario)
                                .adicionarSubMenu(2, "Menu Livros", menuLivros)
                                .adicionarSubMenu(3, "Menu Espera", menuEspera)
                                .adicionarSubMenu(4, "Menu Historico", menuHistorico)
                                .adicionarSubMenu(5, "Menu Recomendacoes", menuRecomendacao)
                                .adicionarOpcao(0, "Sair", null);
        }
}
