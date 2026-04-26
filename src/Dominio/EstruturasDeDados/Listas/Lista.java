package Dominio.EstruturasDeDados.Listas;

import java.util.Iterator;

// wrapper da lista encadeada para simplificar a interface
public class Lista<T> implements IListas<T> {

    protected final ListaEncadeada<T> lista;
    protected int contagem;

    public Lista() {
        lista = new ListaEncadeada<>();
    }

    public int Inserir(T dado) {
        if (dado == null)
            return -1;
        lista.InserirFim(dado);
        return contagem++;
    }

    // retira proximo item da fila
    public T Retirar() {
        T dado = Topo();
        Remover(0);
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
    public void Remover(T chave) {
        int posicao = 0;
        for (var item : lista) {
            if (item.equals(chave)) {
                lista.Remover(posicao);
                break;
            }
            posicao++;
        }
        return;
    }

    // remove item por indice
    public void Remover(int indice) {
        lista.Remover(indice);
    }

    public int Tamanho() {
        return lista.Tamanho();
    }

    public int Contagem() {
        return contagem;
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
