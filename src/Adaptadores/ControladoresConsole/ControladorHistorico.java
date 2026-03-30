package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.IServico;
import Dominio.Modelos.Usuario;

public class ControladorHistorico implements IControlador {
    public IServico<Usuario> servico;
    private final Scanner scanner;

    public ControladorHistorico(IServico<Usuario> servico, Scanner scanner) {
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