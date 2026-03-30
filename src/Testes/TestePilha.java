// DEMONSTRAÇÃO DE PILHAS/STACK 
// LIFO = LAST IN FIRST OUT

package Testes;

import Dominio.EstruturasDeDados.Listas.Pilha;

public class TestePilha {
    public static void main(String[] args) {

        Pilha<Integer> s = new Pilha<>();

        s.Inserir(1);
        s.Inserir(2);
        s.Inserir(3);
        s.Inserir(4);
        s.Inserir(5);
        s.Inserir(6);

        System.out.println("Imprimindo pilha");

        for (var item : s) {
            System.out.println(">>>" + item);
            
        }
    }
}