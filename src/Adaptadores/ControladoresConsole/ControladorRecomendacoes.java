package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ListaDeLivros;
import Aplicacao.CasosDeUso.ListaDeUsuarios;

public class ControladorRecomendacoes implements IControlador {
        public ListaDeLivros servicoLivros;
    public ListaDeUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControladorRecomendacoes(ListaDeLivros servicoLivros, ListaDeUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        this.servico = servico;
    }

    public void Adicionar() {
    }

    public void Visualizar() {
    }

    public void Editar() {
    }

    public void Listar() {
    }

    public void Remover() {
    }
}