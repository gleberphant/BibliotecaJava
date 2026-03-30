// DEMONSTRAÇÃO DE LISTA ENCADEADA
// 

package Testes;

import Dominio.EstruturasDeDados.Listas.ListaEncadeada;

public class TesteLista {

    public static void main(String[] args) {

        ListaEncadeada<Integer> myList = new ListaEncadeada<>();

        myList.InserirInicio(5);
        myList.InserirInicio(2);
        myList.InserirInicio(3);
        myList.InserirInicio(4);

        // percorre toda a lista

        for (var item : myList) {
            System.out.println(">>>" + item);
            
        }

    }
}