package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoUsuarios;

public class ControladorHistorico  {
    public ServicoUsuarios servico;
    private final Scanner scanner;

    public ControladorHistorico(ServicoUsuarios servico, Scanner scanner) {
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