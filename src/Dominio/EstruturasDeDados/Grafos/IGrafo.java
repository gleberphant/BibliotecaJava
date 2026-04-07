package Dominio.EstruturasDeDados.Grafos;

import java.util.List;

public interface IGrafo<T> extends Iterable<T> {

    public T InserirItem(T valor);

    public void RemoverItem(T chave);

    public List<T> InserirConexao(T chave1, T chave2);

    public List<T> VerConexoes(T chave);

}