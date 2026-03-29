package Dominio.EstruturasDeDados.Listas;

import java.util.Iterator;


public class Lista<T> implements Iterable<T> {

    final ListaEncadeada<T> lista;

    public Lista() {
        lista = new ListaEncadeada<>();
    }

    public boolean Inserir(T dado) {
        lista.InserirFim(dado);
        return true;
    }

    // retira proximo item da fila
    public T Retirar() {
        T dado = Topo();
        Remover();
        return dado;
    }

    // visualiza proximo item sem remover
    public T Topo() {
        return lista.Get(0);
    }

    // remove proximo item
    public void Remover() {
        lista.Remover(0);
    }

    public int Tamanho() {
        return lista.Tamanho();
    }

    // percorre a fila sem remover
    public Iterator<T> iterator() {
        // a iteração segue normal pq estou inserindo no fim da lista
        return lista.iterator();
    }
}
