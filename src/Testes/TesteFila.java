// DEMONSTRAÇÃO DE FILA/QUEUE 
// FIFO = FIRST IN FIRST OUT

package Testes;

import Infraestrutura.EstruturasDeDados.Listas.Fila;

public class TesteFila {
    public static void main(String[] args) {

        Fila<Integer> q = new Fila<>();

        q.Inserir(1);
        q.Inserir(2);
        q.Inserir(3);

        System.out.println("Imprimindo fila");

        for (var item : q) {
            System.out.printf("\n item %s", item);
        }
    }
}
