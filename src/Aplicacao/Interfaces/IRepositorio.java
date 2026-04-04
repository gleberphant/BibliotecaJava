package Aplicacao.Interfaces;

public interface IRepositorio<T> extends Iterable<T> {

    // inserir um item
    public int Inserir(T dado);

    // retira proximo item da fila
    public T Retirar();

    // retira proximo item da fila
    public T Ultimo();

    // visualiza proximo item sem remover
    public T Topo();

    // remove proximo item
    public void Remover();

    // remove  item de indice
    public void Remover(int indice);

    public void InserirConexao(T t1, T t2);

        // quantidade de itens no repositorio
    public int Tamanho();

    // converter os dados do repositorio para uma string
    public String toString();

}
