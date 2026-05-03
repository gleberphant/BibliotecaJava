package Testes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Dominio.MinhasEstruturasDeDados.Grafos.GrafoHash;

public class TestadorGrafos<T extends Comparable<T>> {
    public static void main(String[] args) {
        new TestadorGrafos<>().Executar();
    }

    public void Executar() {
        var grafo = MockarGrafo(new GrafoHash<>());

        System.out.println("\n EXIBINDO GRAFO");
        System.out.println(grafo.toString());

    }

    // Para mockar GRAFO
    public GrafoHash<T> MockarGrafo(GrafoHash<T> grafo) {
        int numItems = 10 + (int) (Math.random() * 20);
        return MockarGrafo(grafo, numItems);
    }

    @SuppressWarnings("unchecked")
    public GrafoHash<T> MockarGrafo(GrafoHash<T> grafo, int numItens) {
        // inserirr itens
        System.out.print("\nItens gerados > ");
        for (int i = 0; i < numItens; i++) {
            // Gera o valor e converte para T
            Integer valor = (int) (Math.random() * 100);
            grafo.InserirItem((T) valor);
            System.out.print("{" + valor + "} ");
        }
        System.out.print("\n > " + numItens + " itens gerados.");

        // inserirr conexoes aleatorias
        int numConexoes = numItens + (int) (Math.random() * 20);
        for (int i = 0; i < numConexoes; i++) {

            // criar conexoes aleatorias
            T inicio = grafo.GetAleatorio();
            T fim = grafo.GetAleatorio();

            grafo.InserirConexao(inicio, fim);

            //chance de repetir a conexao para aumentar o peso
            while (Math.random() < 0.3)
                grafo.InserirConexao(inicio, fim);
            // System.out.println("Conexao Gerada: " + item1 + " ->" + item2);
        }
        System.out.print("\n > " + numConexoes + " Conexoes Geradas.");

        return grafo;

    }

    public String ImprimeGrafoArvore(Map<T, Map<T, Integer>> mapaAdjacencias) {
        if (mapaAdjacencias.isEmpty())
            return "{}";

        StringBuilder sb = new StringBuilder();
        Set<T> visitados = new HashSet<>();

        // Percorre todos os nós para garantir que grafos desconexos sejam mostrados
        for (T noRaiz : mapaAdjacencias.keySet()) {
            if (!visitados.contains(noRaiz)) {
                renderizarNo(noRaiz, mapaAdjacencias, "", true, sb, visitados);
            }
        }
        return sb.toString();
    }

    private void renderizarNo(T no, Map<T, Map<T, Integer>> grafo, String prefixo,
            boolean ultimo, StringBuilder sb, Set<T> visitados) {

        sb.append(prefixo)
                .append(ultimo ? "└── " : "├── ")
                .append(no)
                .append("\n");

        // Marcar como visitado para evitar recursão infinita em grafos com ciclos
        if (visitados.contains(no)) {
            // Se o nó já foi visitado, indica a referência para não entrar em loop
            sb.insert(sb.length() - 1, " (recursivo)");
            return;
        }
        visitados.add(no);

        Map<T, Integer> vizinhos = grafo.get(no);
        if (vizinhos != null && !vizinhos.isEmpty()) {
            List<T> chaves = new ArrayList<>(vizinhos.keySet());

            for (int i = 0; i < chaves.size(); i++) {
                boolean ehUltimo = (i == chaves.size() - 1);
                String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");
                renderizarNo(chaves.get(i), grafo, novoPrefixo, ehUltimo, sb, visitados);
            }
        }
    }

}
