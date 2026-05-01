package Dominio.Algoritmos;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import Dominio.EstruturasDeDados.Listas.Lista;

public class BuscaDijkstra<T> {

    // busca o menor caminho usando o dijkstra.
    public Map<T, T> BuscarCaminho(Map<T, Map<T, Integer>> grafo, T inicio, T fim) {

        HashMap<T, Integer> distanciasAcumuladas = new HashMap<>();
        LinkedHashMap<T, T> caminho = new LinkedHashMap<>();

        for (var item : grafo.entrySet()) {
            distanciasAcumuladas.putIfAbsent(item.getKey(), Integer.MAX_VALUE);
        }

        LinkedList<T> fila = new LinkedList<>();

        distanciasAcumuladas.put(inicio, 0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            T pai = fila.poll();

            if (pai.equals(fim)) {
                break;
            }

            var filhos = grafo.get(pai);

            for (var no : filhos.entrySet()) {

                T filho = no.getKey();
                int distanciaFilho = no.getValue();
                int distanciaAcumuladaPai = distanciasAcumuladas.get(pai);
                int distanciaAcumuladaFilho = distanciasAcumuladas.get(filho);

                // se custo acumulado pai+ custo filho < custo acumulado filho

                if ((distanciaAcumuladaPai + distanciaFilho) < distanciaAcumuladaFilho) {
                    distanciasAcumuladas.put(filho, distanciaAcumuladaPai + distanciaFilho);
                    caminho.put(filho, pai);
                }

                fila.add(filho);
            }

        }

        return caminho;
    }

    public Lista<T> ConverteCaminhoParaLista(Map<T, T> caminho, T inicio, T fim) {

        Lista<T> lista = new Lista<>();

        T atual = fim;
        while (atual != null) {

            lista.Inserir(atual);

            if (atual.equals(inicio))
                break;

            atual = caminho.get(atual);

        }

        return lista;
    }

}
