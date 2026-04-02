package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.CasosDeUso.ServicoLivros;
import Aplicacao.CasosDeUso.ServicoUsuarios;

public class ControleRecomendacoes {
        public ServicoLivros servicoLivros;
    public ServicoUsuarios servicoUsuarios;
    private final Scanner scanner;

    public ControleRecomendacoes(ServicoLivros servicoLivros, ServicoUsuarios servicoUsuarios, Scanner scanner) {
        this.scanner = scanner;
        //this.servico = servico;
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