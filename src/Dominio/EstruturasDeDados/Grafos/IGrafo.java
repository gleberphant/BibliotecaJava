package Dominio.EstruturasDeDados.Grafos;

import java.util.Map;

import Dominio.EstruturasDeDados.Listas.Lista;

public interface IGrafo<T> extends Iterable<T> {

    public T InserirItem(T valor);

    public void RemoverItem(T chave);

    public Map<T, Integer> InserirConexao(T chave1, T chave2);

    public Map<T, Integer> MapaDeConexoes(T chave);

    public Map<T, T> BuscarCaminho(T inicio, T fim);

    public Lista<T> BuscarCaminhoLista(T inicio, T fim);

}