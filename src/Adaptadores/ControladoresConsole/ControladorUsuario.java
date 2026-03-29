package Adaptadores.ControladoresConsole;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;

public class ControladorUsuario implements IControlador {
    public IServico servico;

    public ControladorUsuario(IServico servico) {
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