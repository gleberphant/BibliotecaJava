package Dominio.EstruturasDeDados.Listas;

import java.util.Iterator;

// wrapper da lista encadeada para simplificar a interface
public class Lista<T> implements Iterable<T> {

    protected final ListaEncadeada<T> lista;

    public Lista() {
        lista = new ListaEncadeada<>();
    }

    public boolean Inserir(T dado) {
        if (dado == null)
            return false;
        lista.InserirFim(dado);
        return true;
    }

    // retira proximo item da fila
    public T Retirar() {
        T dado = Topo();
        Remover();
        return dado;
    }

    // retira último item da fila
    public T Ultimo() {
        return lista.GetUltimo();
    }

    // visualiza proximo item sem remover
    public T Topo() {
        return lista.Get(0);
    }

    // remove proximo item
    public void Remover() {
        lista.Remover(0);
    }

    // remove item por indice
    public void Remover(int indice) {
        lista.Remover(indice);
    }

    public int Tamanho() {
        return lista.Tamanho();
    }

    public String toString() {
        return lista.toString();
    }

    // percorre a fila sem remover
    @Override
    public Iterator<T> iterator() {
        // a iteração segue normal pq estou inserindo no fim da lista
        return lista.iterator();
    }
}
