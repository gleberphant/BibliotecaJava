package Aplicacao.Interfaces;

import Dominio.Modelos.Usuario;
import Infraestrutura.EstruturasDeDados.Listas.Lista;

public interface IRepositorioUsuario extends Iterable<Usuario> {

    // inserir um item
    public int Inserir(Usuario dado);

    public Usuario BuscarID(int ID);

    public Usuario BuscarNome(String nome);

    public Lista<Usuario> Listar();

    // remove item de indice
    public boolean Remover(int ID);

    public int Contagem();

    // quantidade de itens no repositorio
    public int Tamanho();

    // converter os dados do repositorio para uma string
    public String toString();

}
