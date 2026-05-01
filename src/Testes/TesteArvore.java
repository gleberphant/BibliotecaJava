// DEMONSTRAÇÃO DE LISTA ENCADEADA
// 

package Testes;

import Dominio.MinhasEstruturasDeDados.Arvores.ArvoreBinaria;

public class TesteArvore {

    public static void main(String[] args) {

        ArvoreBinaria<Integer> a = new ArvoreBinaria<>();

        int maxItens = (int) (Math.random() * 100);

        for (int i = 0; i < maxItens; i++) {
            a.Inserir((int) (Math.random() * 100));
        }

        // percorre toda a lista

        System.out.println(a.toString());

    }
}