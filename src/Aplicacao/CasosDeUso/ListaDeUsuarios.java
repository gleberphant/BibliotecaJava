package Aplicacao.CasosDeUso;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Adaptadores.Repositorios.IRepositorio;

import Dominio.Modelos.Usuario;

public class ListaDeUsuarios {
    ArrayList<Usuario> listaUsuarios;
    IRepositorio repositorio;

    public ListaDeUsuarios(IRepositorio repositorio) {
        listaUsuarios = new ArrayList<Usuario>();
        this.repositorio = repositorio;

    }

    public Boolean Adicionar(Usuario novo) {
        novo.ID = (listaUsuarios.size() == 0) ? 0 : listaUsuarios.getLast().ID + 1;
        listaUsuarios.add(novo);
        return true;

    }

    public Usuario Visualizar(String stringID) {
        int ID = validaID(stringID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        return Visualizar(ID);
    }

    public Usuario Visualizar(int ID) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.ID == ID) {
                return usuario;
            }
        }

        throw new NoSuchElementException("Livro não encontrado");
        // return null;
    }

    public Usuario[] Listar() {
        int indice = 0;
        Usuario[] lista = new Usuario[listaUsuarios.size()];

        for (var usuario : listaUsuarios) {
            lista[indice] = usuario;
            indice++;
        }

        return lista;

    }

    public Boolean Editar(String stringID, String nome, String cpf) {

        int ID = validaID(stringID);
        for (var usuario : listaUsuarios) {

            if (usuario.ID == ID) {
                usuario.Nome = nome;
                usuario.CPF = cpf;
                return true;
            }

        }

        return false;

    }

    public void Remover(String stringID) {

        int ID = validaID(stringID);

        int indice = 0;

        for (var usuario : listaUsuarios) {

            if (usuario.ID == ID) {
                listaUsuarios.remove(indice);
                return;
            }
            indice++;
        }

        throw new NoSuchElementException("Livro não encontrado");

    }

    private int validaID(String stringID) {

        try {
            return Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }
}
