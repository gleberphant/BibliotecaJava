package Dominio.EstruturasDeDados.Grafos;

import java.util.HashMap;



public interface IGrafo<T> extends Iterable<T> {

    public T InserirItem(T valor);

    public void RemoverItem(T chave);

    public HashMap<T, Integer> InserirConexao(T chave1, T chave2);

    public  HashMap<T, Integer> VerConexoes(T chave);

}