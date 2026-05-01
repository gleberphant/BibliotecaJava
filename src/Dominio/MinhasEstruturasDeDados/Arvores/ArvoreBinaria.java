package Dominio.MinhasEstruturasDeDados.Arvores;
//implementar

import java.util.LinkedList;

import java.util.NoSuchElementException;

import java.util.Iterator;

public class ArvoreBinaria<T extends Comparable<T>> implements Iterable<T> {
    // Coloquei no como classe interna privada para encapsulamento

    static class NoArvore<T> {

        public T dado;
        NoArvore<T> filhoEsquerdo;
        NoArvore<T> filhoDireito;

        NoArvore(T dado) {
            this.dado = dado;
        }

    }

    NoArvore<T> raiz;
    int tamanho;

    public ArvoreBinaria() {
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

    public T Topo() {
        return raiz.dado;
    }

    public T Buscar(T chave) {
        return buscaRecursiva(chave, raiz).dado;
    }

    // remove item
    public void Remover(T chave) {
        removerRecursivo(chave, raiz);
    }

    private NoArvore<T> buscaRecursiva(T chave, NoArvore<T> pai) {
        // 1. Caso Base: O dado não foi encontrado ou a árvore/subárvore está vazia
        if (pai == null) {
            return null;
        }

        int comparacao = chave.compareTo(pai.dado);

        // 2. Caso Base: Encontrou o nó
        if (comparacao == 0) {
            return pai;
        }

        // 3. Navegação: Decide para qual lado ir
        if (comparacao < 0) {
            return buscaRecursiva(chave, pai.filhoEsquerdo);
        } else {
            return buscaRecursiva(chave, pai.filhoDireito);
        }
    }

    private NoArvore<T> buscaPaiRecursivo(T chave, NoArvore<T> pai) {

        // retorna vazio se nao encontrou no ou o pai é raiz
        if (pai == null)
            return null;

        int comparacao = chave.compareTo(pai.dado);

        // verifica se é o filho esquerdo
        if (comparacao > 0) {
            if (pai.filhoEsquerdo != null && chave.compareTo(pai.filhoEsquerdo.dado) == 0)
                return pai;
            return buscaPaiRecursivo(chave, pai.filhoEsquerdo);
        }
        // verifica se é o filho direito
        else {
            if (pai.filhoDireito != null && chave.compareTo(pai.filhoDireito.dado) == 0)
                return pai;
            return buscaPaiRecursivo(chave, pai.filhoDireito);
        }

    }

    private NoArvore<T> removerRecursivo(T chave, NoArvore<T> atual) {

        if (atual == null)
            return null;

        int comparacao = chave.compareTo(atual.dado);

        if (comparacao < 0) {
            atual.filhoEsquerdo = removerRecursivo(chave, atual.filhoEsquerdo);
            return atual;
        }
        if (comparacao > 0) {
            atual.filhoDireito = removerRecursivo(chave, atual.filhoDireito);
            return atual;
        }
        // encontrou o no
        if (comparacao == 0) {

            // caso 1 - no sem filhos
            if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
                tamanho--;
                return null;
            }

            // caso 2 - no com 1 filho
            if (atual.filhoEsquerdo == null) {
                tamanho--;
                return atual.filhoDireito;
            }

            if (atual.filhoDireito == null) {
                tamanho--;
                return atual.filhoEsquerdo;
            }

            // caso 3 - no com 2 filhos
            // sucesssor é o maior do ramo esquerdo
            var sucessor = atual.filhoEsquerdo;
            while (sucessor.filhoDireito != null) {
                sucessor = sucessor.filhoDireito;
            }

            // transfere os dados
            atual.dado = sucessor.dado;

            // remove o nó substituto original da subárvore esquerda
            atual.filhoEsquerdo = removerRecursivo(sucessor.dado, atual.filhoEsquerdo);

            return atual;

        }
        return atual;

    }

    public int Tamanho() {
        return tamanho;
    }

    // string em formato json
    public String toString() {
        return gerarASCII(this.raiz, "", true);
    }

    private String gerarASCII(NoArvore<T> no, String prefixo, boolean eUltimo) {
        if (no == null)
            return "";

        StringBuilder sb = new StringBuilder();

        // Adiciona o prefixo do nível atual
        sb.append(prefixo);

        // Define o caractere de ramificação (galho)
        sb.append(eUltimo ? "└── " : "├── ");

        // Adiciona o dado do nó
        sb.append(no.dado).append("\n");

        // Prepara o prefixo para os filhos
        String novoPrefixo = prefixo + (eUltimo ? "    " : "│   ");

        // Chama recursivamente para os filhos
        // Precisamos tratar a exibição para garantir que o desenho não quebre
        if (no.filhoEsquerdo != null || no.filhoDireito != null) {
            if (no.filhoEsquerdo != null) {
                sb.append(gerarASCII(no.filhoEsquerdo, novoPrefixo, no.filhoDireito == null));
            }
            if (no.filhoDireito != null) {
                sb.append(gerarASCII(no.filhoDireito, novoPrefixo, true));
            }
        }

        return sb.toString();
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
