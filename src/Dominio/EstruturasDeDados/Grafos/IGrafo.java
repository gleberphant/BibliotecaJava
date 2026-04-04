package Dominio.EstruturasDeDados.Grafos;

public interface IGrafo<T> {

    public Integer InserirNo(T valor);

    public Integer InserirConexao(int chave1, int chave2);

}