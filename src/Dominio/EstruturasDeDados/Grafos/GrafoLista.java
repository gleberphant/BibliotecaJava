package Dominio.EstruturasDeDados.Grafos;

import java.util.ArrayList;

public class GrafoLista<T> {

    public record Aresta<T>(T origem, T destino, int peso) {
    }

    ArrayList<T> ListaNos;
    ArrayList<Aresta<T>> ListaArestas;

    public GrafoLista() {
        ListaNos = new ArrayList<>();
        ListaArestas = new ArrayList<>();
    }

    public void InserirNo(T valor) {
        for (int i = 0; i < ListaNos.size(); i++) {
            if (ListaNos.get(i) == valor)
                return;
        }

        ListaNos.add(valor);
    }

    public void InserirConexao(T valor1, T valor2) {
        T no1 = null, no2 = null;

        // se ambos nos forem iguais retorna
        if (valor1 == valor2)
            return;

        // verifica se os dois nos existem
        for (int i = 0; i < ListaNos.size(); i++) {
            if (ListaNos.get(i) == valor1) {
                no1 = ListaNos.get(i);
            }

            if (ListaNos.get(i) == valor2) {
                no2 = ListaNos.get(i);
            }

            if (no1 != null && no2 != null)
                break;
        }

        if (no1 == null || no2 == null)
            return;

        // procura se a aresta existe ,
        for (int i = 0; i < ListaArestas.size(); i++) {

            Aresta<T> temp = ListaArestas.get(i);

            // se encontrar a areas entao aumenta o peso e retorna
            if (temp.origem == no1 && temp.destino == no2) {

                ListaArestas.set(i, new Aresta<T>(no1, no2, temp.peso + 1));
                return;
            }
        }

        // se não encontrar a areas cria a aresta
        ListaArestas.add(new Aresta<>(no1, no2, 1));

        return;
    }

    public String toString() {

        StringBuilder mensagem = new StringBuilder();

        mensagem.append("Nós {");

        for (int i = 0; i < ListaNos.size(); i++) {
            mensagem.append(" " + ListaNos.get(i));
            if (i < ListaNos.size() - 1)
                mensagem.append(",");
        }

        mensagem.append(" }\n");
        mensagem.append("Vértices \n");

        for (int i = 0; i < ListaArestas.size(); i++) {
            Aresta<T> temp = ListaArestas.get(i);
            mensagem.append("   (" + temp.origem + " -- " + temp.destino + ")" + " Peso:" + temp.peso + "\n");

        }

        return mensagem.toString();
    }

    public void Imprimir() {
        System.out.print(this.toString());

    }
}
