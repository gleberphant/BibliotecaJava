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

        while (iterador.hasNext()) {
            dado = iterador.next();
        }

        return dado;

    }

    // visualiza a raiz da arvore
    public T Topo() {
        return raiz.dado;

    }

    public NoArvore<T> ProcurarNoSubarvore(T dado, NoArvore<T> inicio) {

        var ponteiro = inicio;
        while (true) {

            if (ponteiro.dado.compareTo(dado) < 0)
                ponteiro = ponteiro.filhoEsquerdo;

            if (ponteiro.dado.compareTo(dado) > 0)
                ponteiro = ponteiro.filhoEsquerdo;

            if (ponteiro.dado == dado)
                return ponteiro;
        }

    }

    // procura no com o dado
    public NoArvore<T> ProcurarNo(T dado) {
        return ProcurarNoSubarvore(dado, raiz);
    }

    // remove proximo item
    public void Remover(T dado) {

        var no = ProcurarNo(dado);

        // caso 1 remover sem filhos
        if (no.filhoEsquerdo == null && no.filhoDireito == null) {
            no = null;
            return;
        }

        // caso 2 remover pai de 1 filho
        // **se filho esquerdo é nulo. então o no vira filho direito
        if (no.filhoEsquerdo == null) {
            no = no.filhoDireito;
            return;
        }

        // **se filho direito é nulo. então o no vira filho esquerda
        if (no.filhoDireito == null) {
            no = no.filhoEsquerdo;
            return;
        }

        // caso 3 remover pai de 2 filhos - precisa rebalancer a bagaça
        // Encontra o sucessor (maior elementado da subárvore esquerda)
        var sucessor = no.filhoEsquerdo;
        while (sucessor.filhoDireito != null) {
            sucessor = sucessor.filhoDireito;
        }

        sucessor.filhoDireito = no.filhoDireito;
        no = sucessor;

    }

    public NoArvore<T> removerRecursivo(NoArvore<T> atual, T dado) {

        if (atual == null)
            return null;

        int comparacao = dado.compareTo(atual.dado);

        if (comparacao < 0) {
            atual.filhoEsquerdo = removerRecursivo(atual.filhoEsquerdo, dado);
            return atual;
        }
        if (comparacao > 0) {
            atual.filhoDireito = removerRecursivo(atual.filhoDireito, dado);
            return atual;
        }
        // encontrou o no
        if (comparacao == 0) {
            tamanho--;

            // caso 1 - no sem filhos
            if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
                return null;
            }

            // caso 2 - no com 1 filho
            if (atual.filhoEsquerdo == null) {
                return atual.filhoDireito;
            }

            if (atual.filhoDireito == null) {
                return atual.filhoEsquerdo;
            }

            // caso 3 - no com 2 filhos
            // sucesssor é o  maior do ramo esquerdo
            var sucessor = atual.filhoEsquerdo;
            while (sucessor.filhoDireito != null) {
                sucessor = sucessor.filhoDireito;
            }

            //o no removido vira o maior do ramo esquerdo
            sucessor.filhoDireito = atual.filhoDireito;
            atual = sucessor;

            //  remove o nó substituto original da subárvore esquerda
            atual.filhoEsquerdo = removerRecursivo(atual.filhoEsquerdo, atual.dado);

            return atual;

        }
        return atual;

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
