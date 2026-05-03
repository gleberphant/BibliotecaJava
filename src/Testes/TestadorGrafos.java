package Testes;

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
        int numItems = 5 + (int) (Math.random() * 20);
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
        int numConexoes = 10 + (int) (Math.random() * 10);
        for (int i = 0; i < numConexoes; i++) {

            // criar conexoes aleatorias
            T item1 = grafo.GetAleatorio();
            T item2 = grafo.GetAleatorio();

            // se forem iguais re rola ate fica diferente
            while ((item1 == null || item2 == null) || (item1.compareTo(item2) == 0)) {
                item1 = grafo.GetAleatorio();
                item2 = grafo.GetAleatorio();
            }

            grafo.InserirConexao(item1, item2);
            // System.out.println("Conexao Gerada: " + item1 + " ->" + item2);
        }
        System.out.print("\n > " + numConexoes + " Conexoes Geradas.");

        return grafo;

    }
}
