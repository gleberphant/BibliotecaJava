package Infraestrutura.Roteadores;

import java.util.Map;
import java.util.Scanner;

public interface IComando {

    public IComando adicionarOpcao(int chave, String texto, Runnable acao);

    public IComando adicionarSubMenu(int chave, String texto, IComando proximoRoteador);

    public IComando Roteamento(int select);

    public Map<Integer, String> GetMenu();

    public IComando SetScanner(Scanner in);

    public Scanner GetScanner();

    public String GetTitulo();

    public void Voltar();
}