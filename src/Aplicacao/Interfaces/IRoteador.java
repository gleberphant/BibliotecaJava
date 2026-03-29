package Aplicacao.Interfaces;

import java.util.Map;

public interface IRoteador {

    public Map<Integer, String> GetMenu();
    public IRoteador Roteamento(int select);
    String GetTitulo();
}