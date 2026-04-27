package Dominio.EstruturasDeDados.Grafos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.StringJoiner;

public class GrafoHash<T> implements IGrafo<T> {

    // mapa de adjacências

    // { no_origem1 :{ no_destino1: peso , no_destino2: peso, .... }, ... }

    private HashMap<T, HashMap<T, Integer>> mapaAdjacencias;

    public GrafoHash() {
        this.mapaAdjacencias = new LinkedHashMap<>();

    }

    public T InserirItem(T item) {

        this.mapaAdjacencias.putIfAbsent(item, new HashMap<>());

        return item;

    }

    public HashMap<T, Integer> InserirConexao(T chave1, T chave2) {
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

    public HashMap<T, Integer> VerConexoes(T chave) {

        return mapaAdjacencias.get(chave);

    }

    // busca o menor caminho usando o dijkstra.
    public HashMap<T, T> BuscarCaminho(GrafoHash<T> grafo, T inicio, T Fim) {

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

        StringJoiner sj = new StringJoiner(", ", "{", "}");

        for (var item : mapaAdjacencias.entrySet()) {

            sj.add(String.format("'%d': %s",
                    item.getKey(),
                    item.getValue().toString()));
        }

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