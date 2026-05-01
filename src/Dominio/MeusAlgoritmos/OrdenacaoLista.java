package Dominio.MeusAlgoritmos;

import Dominio.MinhasEstruturasDeDados.Listas.*;

public class OrdenacaoLista<T> {

    public Lista<T> BubbleSort(Lista<T> lista) {
        if (lista == null || lista.Tamanho() <= 1) {
            return lista;
        }

        boolean trocou;

        do {
            trocou = false;
            // Percorre a lista até n-1
            var iterador = lista.iterator();

            while (iterador.hasNext()) {
                var atual = iterador.next();
                var proximo = iterador.next();

                if (proximo == null)
                    break;

                if (((Comparable<T>) atual).compareTo(proximo) > 0) {
                    // Realiza a troca na estrutura da lista
                    proximo = atual;
                    atual = proximo;
                    trocou = true;
                }

            }

        } while (trocou);

        return lista;
    }

}
