package Dominio.Algoritmos;


import java.util.HashMap;
import java.util.LinkedList;


import Dominio.EstruturasDeDados.Grafos.GrafoHash;

public class BuscaCaminho<T> {

    // busca o menor caminho usando o dijkstra.
    public HashMap<T, T> Dijkstra(GrafoHash<T> grafo, T inicio, T Fim) {

        HashMap<T, Integer> distanciasAcumuladas = new HashMap<>();
        HashMap<T, T> caminho = new HashMap<>();

        for (var item : grafo) {
            distanciasAcumuladas.putIfAbsent(item, Integer.MAX_VALUE);
        }

        LinkedList<T> fila = new LinkedList<>();

        distanciasAcumuladas.put(inicio, 0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            T pai = fila.poll();

            if (pai.equals(Fim)) {
                break;
            }

            var filhos = grafo.VerConexoes(pai);

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

}
