package Dominio.MinhasEstruturasDeDados.Listas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEncadeada<T extends Comparable<T>> implements Iterable<T> {

    // Nó como classe interna privada para Encapsulamento
    public static class No<T> {
        T dado;
        public No<T> proximo;

        No(T dado) {
            this.dado = dado;
        }
    }

    private No<T> primeiro;
    private No<T> ultimo;
    private int tamanho;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    protected boolean InserirInicio(T dado) {
        No<T> novo = new No<>(dado);
        if (primeiro == null) {
            primeiro = ultimo = novo;
        } else {
            novo.proximo = primeiro;
            primeiro = novo;
        }
        tamanho++;
        return true;
    }

    protected boolean InserirFim(T dado) {
        No<T> novo = new No<>(dado);
        if (primeiro == null) {
            primeiro = ultimo = novo;
        } else {
            ultimo.proximo = novo; // O(1)
            ultimo = novo;
        }
        tamanho++;
        return true;
    }

    protected T Get(int indice) {
        No<T> alvo = GetNo(indice);
        return (alvo == null) ? null : alvo.dado;
    }

    protected T GetPrimeiro() {
        return primeiro.dado;
    }

    protected T GetUltimo() {
        return ultimo.dado;
    }

    protected No<T> GetNo(int indice) {
        if (indice < 0 || indice >= tamanho)
            return null;
        No<T> atual = primeiro;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual;
    }

    protected void Set(int indice, T novo) {
        GetNo(indice).dado = novo;

    }

    protected void Remover(int indice) {
        if (indice < 0 || indice >= tamanho)
            return;

        if (indice == 0) {
            primeiro = primeiro.proximo;
            if (primeiro == null)
                ultimo = null;
            tamanho = 0;
            return;
        } else {
            No<T> anterior = GetNo(indice - 1);

            if (anterior.proximo == null)
                ultimo = anterior;
            else
                anterior.proximo = anterior.proximo.proximo;
            tamanho--;
            return;
        }

    }

    protected int Tamanho() {
        return tamanho;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (var item : this) {

            sb.append(item.toString());
        }

        return sb.toString();

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public No<T> atual = primeiro;

            @Override
            public boolean hasNext() {
                return (atual == null) ? false : true;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T dado = atual.dado;
                atual = atual.proximo;
                return dado;
            }
        };
    }
}