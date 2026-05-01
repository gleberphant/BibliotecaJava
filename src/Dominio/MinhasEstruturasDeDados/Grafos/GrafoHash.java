package Dominio.MinhasEstruturasDeDados.Grafos;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

import Dominio.MinhasEstruturasDeDados.Listas.Lista;
import Dominio.MeusAlgoritmos.BuscaDijkstra;

public class GrafoHash<T> implements IGrafo<T> {

    // mapa de adjacências

    // { no_origem1 :{ no_destino1: peso , no_destino2: peso, .... }, ... }

    private Map<T, Map<T, Integer>> mapaAdjacencias;

    public GrafoHash() {
        this.mapaAdjacencias = new LinkedHashMap<>();

    }

    public T InserirItem(T item) {

        this.mapaAdjacencias.putIfAbsent(item, new HashMap<>());

        return item;

    }

    public Map<T, Integer> InserirConexao(T chave1, T chave2) {
        // impede relações recursivas.
        if (chave1.equals(chave2))
            return null;

        // checar se os dois objetos existem. senão cria o inexistente
        if (!mapaAdjacencias.containsKey(chave1))
            mapaAdjacencias.putIfAbsent(chave1, new HashMap<>());

        if (!mapaAdjacencias.containsKey(chave2))
            mapaAdjacencias.putIfAbsent(chave2, new HashMap<>());

        // Se a conexão já existe, incrementa o peso
        if (mapaAdjacencias.get(chave1).containsKey(chave2)) {
            int pesoAtual = mapaAdjacencias.get(chave1).get(chave2);
            mapaAdjacencias.get(chave1).put(chave2, pesoAtual + 1);
        }
        // Se não existe, cria a conexão com peso inicial (ex: 1)
        else {
            mapaAdjacencias.get(chave1).put(chave2, 1);
        }

        return mapaAdjacencias.get(chave1);

    }

    public Map<T, Integer> MapaDeConexoes(T chave) {

        return mapaAdjacencias.get(chave);

    }

    // busca o menor caminho usando o dijkstra.
    public Map<T, T> BuscarCaminho(T inicio, T fim) {

        return new BuscaDijkstra<T>().BuscarCaminho(mapaAdjacencias, inicio, fim);
    }

    public void RemoverItem(T chave) {
        mapaAdjacencias.remove(chave);
    }

    // retorna o tamanho atual do mapa
    public int Tamanho() {
        return mapaAdjacencias.size();
    }

    public T GetPrimeiro() {
        if (mapaAdjacencias.isEmpty())
            return null;

        return mapaAdjacencias.keySet().iterator().next();
    }

    public T GetUltimo() {

        T ultimo = null;

        for (var item : mapaAdjacencias.keySet()) {
            ultimo = item;
        }

        return ultimo;

    }

    @Override
    public String toString() {
        if (mapaAdjacencias.isEmpty())
            return "{}";

        var sj = new StringBuilder();

        sj.append(String.format("{\n"));
        for (var item : mapaAdjacencias.entrySet()) {

            sj.append(String.format("'%2d': {", item.getKey()));

            for (var destino : item.getValue().entrySet()) {
                sj.append(String.format("'%d': %d, ", destino.getKey(), destino.getValue()));
            }

            sj.append(String.format("},\n", item.getKey()));
        }

        sj.append(String.format("}"));
        return sj.toString();
    }

    public String toStringArvore() {
        StringBuilder sb = new StringBuilder();

        // Itera sobre cada item (origem) no grafo
        for (T origem : mapaAdjacencias.keySet()) {
            sb.append("Livro [").append(origem.toString()).append("]\n");

            var conexoes = mapaAdjacencias.get(origem);

            if (conexoes == null || conexoes.isEmpty()) {
                sb.append("   └─ Sem conexões\n");
            } else {
                // Itera sobre cada destino (conexão)
                for (var entry : conexoes.entrySet()) {
                    T destino = entry.getKey();
                    int peso = entry.getValue();

                    // Formatação visual
                    sb.append("   └─→ ");
                    sb.append(destino.toString());
                    sb.append(" (Peso: ").append(peso).append(")\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return mapaAdjacencias.keySet().iterator();
    }

}