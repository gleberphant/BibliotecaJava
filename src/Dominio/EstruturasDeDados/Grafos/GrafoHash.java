package Dominio.EstruturasDeDados.Grafos;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;
import java.util.StringJoiner;

public class GrafoHash<T> implements Iterable<T> {

    // mapa de adjacências
    // { no_origem :[ no_destino1, no_destino2 ..... ] }

    private Map<T, Set<T>> mapaAdjacencias;

    private int proximaChave;

    public GrafoHash() {
        this.mapaAdjacencias = new LinkedHashMap<>();
        proximaChave = 0;
    }

    // retorna a chave do no gerado
    public Integer ProximaChave() {
        return proximaChave;
    }

    public Integer InserirNo(T valor) {

        this.mapaAdjacencias.putIfAbsent(valor, new HashSet<>());

        return proximaChave++;
    }

    public T InserirConexao(T chave1, T chave2) {
        // impede relações recursivas.
        if (chave1 == chave2)
            return null;

        // checar se os dois objetos existem
        if (!mapaAdjacencias.containsKey(chave1) || !mapaAdjacencias.containsKey(chave2)) {
            // objetos inexistentes;
            return null;
        }

        mapaAdjacencias.get(chave1).add(chave2);
        return chave1;

    }

    public Set<T> VerConexoes(T chave) {

        return mapaAdjacencias.get(chave);

    }

    // public T Get(int chave) {

    // //return mapaAdjacencias.get(chave);
    // }

    public void Remover(T chave) {

        mapaAdjacencias.remove(chave);
    }

    public int Tamanho() {
        return mapaAdjacencias.size();
    }

    public T GetUltimo() {

        if (mapaAdjacencias.isEmpty()) {
            return null; // Ou throw exception
        }

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