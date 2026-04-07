package Dominio.EstruturasDeDados.Grafos;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import java.util.Map;

import java.util.List;
import java.util.StringJoiner;

public class GrafoHash<T> implements IGrafo<T> {

    // mapa de adjacências
    // { no_origem :[ no_destino1, no_destino2 ..... ] }

    private Map<T, List<T>> mapaAdjacencias;

    public GrafoHash() {
        this.mapaAdjacencias = new LinkedHashMap<>();

    }

    public T InserirItem(T item) {
        this.mapaAdjacencias.putIfAbsent(item, new ArrayList<>());

        return mapaAdjacencias.containsKey(item) ? item : null;

    }

    public List<T> InserirConexao(T chave1, T chave2) {
        // impede relações recursivas.
        if (chave1.equals(chave2))
            return null;

        // checar se os dois objetos existem
        if (!mapaAdjacencias.containsKey(chave1) || !mapaAdjacencias.containsKey(chave2)) {
            // objetos inexistentes;
            return null;
        }

        List<T> item1 = mapaAdjacencias.get(chave1);

        item1.add(chave2);

        return item1;

    }

    public List<T> VerConexoes(T chave) {

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