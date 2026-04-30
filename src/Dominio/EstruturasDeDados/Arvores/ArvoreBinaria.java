package Dominio.EstruturasDeDados.Arvores;
//implementar

import java.util.LinkedList;

import java.util.NoSuchElementException;


import java.util.Iterator;

public class ArvoreBinaria<T extends Comparable<T>> implements Iterable<T> {
    // Coloquei no como classe interna privada para encapsulamento

    static class NoArvore<T> {

        T dado;
        NoArvore<T> filhoEsquerdo;
        NoArvore<T> filhoDireito;

        NoArvore(T dado) {
            this.dado = dado;
        }

    }

    NoArvore<T> raiz;
    int tamanho;

    ArvoreBinaria() {
        raiz = null;
        tamanho = 0;
    }

    public int Inserir(T dado) {
        // tenta balancear arvore para verificar onde deve adicionar o novo no
        var novo = new NoArvore<T>(dado);

        // primeiro verifica se o cabecalho é nullo
        if (raiz == null) {
            raiz = novo;
            tamanho++;
            return tamanho;
        }

        // começa no inicio da arvore
        var ponteiro = raiz;

        while (true) {

            // é menor menor que valor atual ? então vai adicionar no lado esquerdo
            if (novo.dado.compareTo(ponteiro.dado) < 0) {

                // esquerdo esta vazio? então adiciona nele
                if (ponteiro.filhoEsquerdo == null) {
                    ponteiro.filhoEsquerdo = novo;
                    break;
                }

                // senao. se é maior que o esquerdo então move para esquerda. recomeça o loop
                ponteiro = ponteiro.filhoEsquerdo;
                continue;

            }

            else {
                // se direito estiver vazio, ocupa o direito do atual
                if (ponteiro.filhoDireito == null) {
                    ponteiro.filhoDireito = novo;

                    break;
                }

                // senao. se é menor que o direito então move para direita e recomeça o loop
                ponteiro = ponteiro.filhoDireito;
                continue;

            }

        }
        tamanho++;
        return tamanho;
    }

    public T Procurar(T dado) {
        return null;
    }

    // retira o elemento raiz
    public T Retirar() {
        T dado = Topo();
        Remover(0);

        return dado;
    }

    // pega o último dado da arvore
    public T Ultimo() {
        if (raiz == null)
            return null;
        
        T dado = null;
        var iterador = this.iterator();

        while (iterador.hasNext()){
            dado = iterador.next();
        }

        return dado;

    }

    // visualiza a raiz da arvore
    public T Topo() {
        return raiz.dado;

    }

    // remove proximo item
    public void Remover(T chave) {
        var novaArvore = new ArvoreBinaria<>();


        for (var item : this){
            novaArvore.Inserir(item);
        }
    }

    // remove item por indice
    public void Remover(int indice) {

    }

    public int Tamanho() {
        return tamanho;
    }

    // vou iterar a arvore usando BSF para percorrer todos itens
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private LinkedList<NoArvore<T>> fila = new LinkedList<>();

            {
                if (raiz != null)
                    fila.add(raiz);

            }

            @Override
            public boolean hasNext() {
                return (fila.isEmpty()) ? false : true;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                var ponteiro = fila.poll();

                if (ponteiro.filhoEsquerdo != null)
                    fila.add(ponteiro.filhoEsquerdo);
                if (ponteiro.filhoDireito != null)
                    fila.add(ponteiro.filhoDireito);

                return ponteiro.dado;
            }
        };

    }
}
