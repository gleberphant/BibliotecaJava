package Dominio.EstruturasDeDados.Grafos;

import java.util.Iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;
import java.util.StringJoiner;

public class GrafoHash<T> implements IGrafo<T>, Iterable<T> {

    // hash table do grafo
    // { no_origem :[ no_destino1, no_destino2 ..... ] }

    private Map<Integer, T> lista;
    private Map<Integer, Set<Integer>> conexoes;
    private int proximoId;

    public GrafoHash() {
        this.conexoes = new HashMap<>();
        this.lista = new HashMap<>();
        proximoId = 0;
    }

    // retorna a chave do no gerado
    public Integer InserirNo(T valor) {

        int chave = proximoId++;
        this.lista.putIfAbsent(chave, valor);
        this.conexoes.putIfAbsent(chave, new HashSet<>());

        return chave;
    }

    public Integer InserirConexao(int chave1, int chave2) {
        // impede relações recursivas.
        if (chave1 == chave2)
            return null;

        // checar se os dois objetos existem
        if (!lista.containsKey(chave1) || !lista.containsKey(chave2)) {
            // objetos inexistentes;
            return null;
        }

        conexoes.get(chave1).add(chave2);
        return chave1;

    }

    public T Get(int chave){

        return lista.get(chave);
    }

    public void Remover(int chave){

        lista.remove(chave);
        conexoes.remove(chave);
    }

    public int Tamanho(){
        return lista.size();
    }

    public T GetUltimo() {

        if (lista.isEmpty()) {
            return null; // Ou throw exception
        }

        int chave = proximoId;

        while (!lista.containsKey(chave) && chave > 0) {
            chave--;
        }

        return lista.get(chave);

    }

    @Override
    public String toString() {
        if (conexoes.isEmpty())
            return "{}";

        StringJoiner sj = new StringJoiner(", ", "{", "}");

        for (var item : conexoes.entrySet()) {

            sj.add(String.format("%d: %s",
                    item.getKey(),
                    item.getValue().toString()));
        }

        return sj.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return lista.values().iterator();
    }

}