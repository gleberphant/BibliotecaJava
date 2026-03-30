package Aplicacao.Interfaces;

public interface IServico<T> {

    public Boolean Adicionar(T novo);

    public T Visualizar(int id);

    public T[] Listar();

    public Boolean Editar(T novo);

    public Boolean Remover(int id);
}