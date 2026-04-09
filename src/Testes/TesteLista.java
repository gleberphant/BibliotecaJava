// DEMONSTRAÇÃO DE LISTA ENCADEADA
// 

package Testes;

import Dominio.EstruturasDeDados.Listas.Lista;


public class TesteLista {

    public static void main(String[] args) {

        Lista<Integer> myList = new Lista<>();

        myList.Inserir(5);
        myList.Inserir(2);
        myList.Inserir(3);
        myList.Inserir(4);

        // percorre toda a lista

        for (var item : myList) {
            System.out.println(">>>" + item);
            
        }

    }
}