package Aplicacao.Interfaces;

import Dominio.Modelos.Usuario;

public interface IRepositorioUsuario extends Iterable<Usuario> {

    // inserir um item
    public int Inserir(Usuario dado);

    // retira proximo item da fila
    public Usuario Retirar();

    // retira proximo item da fila
    public Usuario Ultimo();

    // visualiza proximo item sem remover
    public Usuario Topo();

    // remove proximo item
    // public void Remover();

    // remove item de indice
    public void Remover(int indice);

    // quantidade de itens no repositorio
    public int Tamanho();

    // converter os dados do repositorio para uma string
    public String toString();

}
