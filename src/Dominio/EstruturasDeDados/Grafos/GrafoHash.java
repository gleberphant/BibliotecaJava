package Dominio.EstruturasDeDados.Grafos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

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

        return mapaAdjacencias.containsKey(item) ? item : null;

    }

    public HashMap<T, Integer> InserirConexao(T chave1, T chave2) {
        // impede relações recursivas.
        if (chave1.equals(chave2))
            return null;

        // checar se os dois objetos existem
        if (!mapaAdjacencias.containsKey(chave1) || !mapaAdjacencias.containsKey(chave2)) {
            // objetos inexistentes;
            return null;
        }

        mapaAdjacencias.get(chave1).putIfAbsent(chave2, 0);

        return null;

    }

    public HashMap<T, Integer> VerConexoes(T chave) {

        return mapaAdjacencias.get(chave);

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

    @Override
    public Iterator<T> iterator() {
        return mapaAdjacencias.keySet().iterator();
    }

}