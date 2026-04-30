package Testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Dominio.EstruturasDeDados.Grafos.GrafoHash;

public class TesteGrafosHash {
    public static void main(String[] args) {

        int itens[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

        GrafoHash<Integer> g = new GrafoHash<Integer>();

        System.out.println("\n Grafo do tipo: " + g.getClass());

        for (Integer item : itens)
            g.InserirItem(item);

        g.InserirConexao(1, 3);
        g.InserirConexao(1, 2);

        g.InserirConexao(2, 3);
        g.InserirConexao(2, 5);
        g.InserirConexao(2, 4);

        g.InserirConexao(4, 7);
        g.InserirConexao(4, 7);
        g.InserirConexao(7, 4);

        g.InserirConexao(7, 8);
        g.InserirConexao(8, 4);
        g.InserirConexao(8, 4);

        g.InserirConexao(8, 12);
        g.InserirConexao(12, 10);

        g.InserirConexao(10, 11);
        System.out.println("GRAFO EM JSON");
        System.out.println(g.toString());

        System.out.println("CAMINHO MAIS CURTO");
        
        for(var item : g.BuscarCaminhoLista(1, 11)){
            System.out.print(" > "+item+" ");
        }
        //imprimirCaminho(g.BuscarCaminho(g, 1, 11), 1, 11);
        
    }

    public static void imprimirCaminho(HashMap<Integer, Integer> caminho, Integer inicio, Integer fim) {
        List<Integer> listaCaminho = new ArrayList<>();
        Integer atual = fim;

        // 1. Volta do fim até o início usando o mapa 'caminho'
        while (atual != null) {
            listaCaminho.add(atual);
            if (atual.equals(inicio))
                break; // Chegamos ao início

            atual = caminho.get(atual); // Pega o antecessor
        }

        // 2. Se o último item não for o início, significa que não há caminho
        if (!listaCaminho.get(listaCaminho.size() - 1).equals(inicio)) {
            System.out.println("Não existe caminho entre " + inicio + " e " + fim);
            return;
        }

        // 3. Inverte a lista (pois voltamos do fim para o início)
        Collections.reverse(listaCaminho);

        // 4. Imprime
        System.out.println("Caminho mais curto encontrado:");
        for (int i = 0; i < listaCaminho.size(); i++) {
            System.out.print(listaCaminho.get(i));
            if (i < listaCaminho.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

}