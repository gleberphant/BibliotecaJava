package Dominio.EstruturasDeDados.Grafos;

import Dominio.EstruturasDeDados.Listas.Lista;

public interface IGrafo<T> extends Iterable<T> {

    public T InserirItem(T valor);

    public void RemoverItem(T chave);

    public Lista<T> InserirConexao(T chave1, T chave2);

    public Lista<T> VerConexoes(T chave);

}