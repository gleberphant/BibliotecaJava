package Dominio.MeusAlgoritmos;

import java.util.Map;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import Dominio.MinhasEstruturasDeDados.Listas.ILista;
import Dominio.MinhasEstruturasDeDados.Listas.Lista;

public class BuscaEmGrafos<T extends Comparable<T>> {

    // Dijkstra - busca caminho mais barato
    public Map<T, T> BuscaDijkstra(Map<T, Map<T, Integer>> grafo, T inicio, T fim) {

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

    // BFS - busca caminho mais curto - utiliza fila
    public Map<T, T> BuscaEmLargura(Map<T, Map<T, Integer>> grafo, T inicio, T fim) {

        HashMap<T, T> caminho = new HashMap<>();
        HashSet<T> processados = new HashSet<>();
        LinkedList<T> fila = new LinkedList<>();

        if (inicio == null || !grafo.containsKey(inicio))
            return null;

        fila.add(inicio);
        caminho.put(inicio, null);

        while (!fila.isEmpty()) {
            T ponteiro = fila.poll();

            // encontrou fim, retorna o caminho traçado
            if (ponteiro.equals(fim)) {
                return caminho; //
            }
            processados.add(ponteiro);

            for (var filho : grafo.get(ponteiro).keySet()) {

                if (processados.contains(filho)) {
                    continue;
                } else {

                    caminho.put(filho, ponteiro);
                    fila.add(filho);
                }

            }

        }
        // Retorna o mapa. Se o destino não foi encontrado,
        // a chave 'fim' não existirá no mapa retornado
        return caminho;
    }

    // DFS - busca mais rápida do caminho- utiliza pilha
    public Map<T, T> BuscaEmProfundidade(Map<T, Map<T, Integer>> grafo, T inicio, T fim) {

        HashMap<T, T> caminho = new HashMap<>();
        HashSet<T> processados = new HashSet<>();
        Stack<T> pilha = new Stack<>();

        if (inicio == null || !grafo.containsKey(inicio))
            return null;

        pilha.add(inicio);
        processados.add(inicio);
        caminho.put(inicio, null);

        while (!pilha.isEmpty()) {
            T ponteiro = pilha.pop();

            // encontrou fim, retorna o caminho traçado
            if (ponteiro.equals(fim)) {
                return caminho; //
            }

            processados.add(ponteiro);

            for (var filho : grafo.get(ponteiro).keySet()) {

                if (processados.contains(filho)) {
                    continue;
                } else {
                    caminho.put(filho, ponteiro);
                    pilha.add(filho);
                }

            }

        }
        // Retorna o mapa. Se o destino não foi encontrado,
        // a chave 'fim' não existirá no mapa retornado
        return caminho;
    }

    public ILista<T> Converter(Map<T, T> caminho, T inicio, T fim) {

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
