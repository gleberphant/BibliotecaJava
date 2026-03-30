package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;
import Dominio.Modelos.Livro;


public class ControladorRecomendacoes implements IControlador {
    public IServico<Livro> servico;
    private final Scanner scanner;

    public ControladorRecomendacoes(IServico<Livro> servico, Scanner scanner) {
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