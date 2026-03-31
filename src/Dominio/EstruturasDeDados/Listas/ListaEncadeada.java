package Dominio.EstruturasDeDados.Listas;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEncadeada<T> implements Iterable<T> {

    // Nó como classe interna privada para Encapsulamento
    private static class No<T> {
        T dado;
        No<T> proximo;

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

    public boolean Inserir(T dado) {
        return InserirFim(dado);
    }

    public boolean InserirInicio(T dado) {
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

    public boolean InserirFim(T dado) {
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

    public T Get(int indice) {
        No<T> alvo = GetNo(indice);
        return (alvo == null) ? null : alvo.dado;
    }

    public T GetPrimeiro() {
        return Get(0);
    }

    public T GetUltimo() {
        return Get(tamanho - 1);
    }

    public No<T> GetNo(int indice) {
        if (indice < 0 || indice >= tamanho)
            return null;
        No<T> atual = primeiro;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual;
    }

    public void RemoverNo(int indice) {
        if (indice < 0 || indice >= tamanho)
            return;

        if (indice == 0) {
            primeiro = primeiro.proximo;
            if (primeiro == null)
                ultimo = null;
        } else {
            No<T> anterior = GetNo(indice - 1);
            anterior.proximo = anterior.proximo.proximo;
            if (anterior.proximo == null)
                ultimo = anterior;
        }
        tamanho--;
    }

    public void Set(int indice, T novo) {
        GetNo(indice).dado = novo;

    }

    public int Tamanho() {
        return tamanho;
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();


        for(var item : this ){

            sb.append(item.toString())
        }

        return sb.toString();
        
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = primeiro;

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