package Dominio.Algoritmos;


import java.util.HashMap;
import java.util.LinkedList;


import Dominio.EstruturasDeDados.Grafos.GrafoHash;

public class BuscaDijkstra<T> {

    // busca o menor caminho usando o dijkstra.
    // recebe no inicio e no fim
    public HashMap<T, T> BuscarCaminho(GrafoHash<T> grafo, T inicio, T Fim) {

        HashMap<T, Integer> acumulado = new HashMap<>();
        HashMap<T, T> caminho = new HashMap<>();

        for (var item : grafo) {
            acumulado.putIfAbsent(item, Integer.MAX_VALUE);
        }

        LinkedList<T> fila = new LinkedList<>();

        acumulado.put(inicio, 0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            var pai = fila.poll();

            if (pai.equals(Fim)) {
                break;
            }

            var filhos = grafo.VerConexoes(pai);

            for (var filho : filhos.entrySet()) {

                // se custo acumulado parai+ custo filh < custo acumulado filho
                int distancia = acumulado.get(pai) + filho.getValue();

                if (distancia < acumulado.get(filho.getKey())) {
                    acumulado.put(filho.getKey(), distancia);
                    caminho.put(filho.getKey(), pai);
                }

                fila.add(filho.getKey());
            }

        }

        return caminho;

    }

}
