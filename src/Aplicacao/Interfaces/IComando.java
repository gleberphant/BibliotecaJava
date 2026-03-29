package Aplicacao.Interfaces;

import java.util.Map;

public interface IComando {

    public IComando adicionarOpcao(int chave, String texto, Runnable acao);

    public IComando adicionarSubMenu(int chave, String texto, IComando proximoRoteador);

    public IComando Roteamento(int select);

    public Map<Integer, String> GetMenu();

    public String GetTitulo();

    public void Voltar();
}