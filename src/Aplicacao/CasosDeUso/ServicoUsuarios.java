package Aplicacao.CasosDeUso;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Adaptadores.Repositorios.EmMemoria.*;
import Aplicacao.Interfaces.IRepositorio;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ServicoUsuarios {
    IRepositorio<Usuario> repositorioUsuarios;

    public ServicoUsuarios(IRepositorio<Usuario> repositorio) {
        repositorioUsuarios = repositorio;

    }

    public int Adicionar(Usuario novo) {
        novo.ID = (repositorioUsuarios.Tamanho() == 0) ? 0 : repositorioUsuarios.Ultimo().ID + 1;
        repositorioUsuarios.Inserir(novo);
        return novo.ID;

    }

    public Usuario Visualizar(String stringID) {
        int ID = validaID(stringID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        return Visualizar(ID);
    }

    public Usuario Visualizar(int ID) {
        for (Usuario usuario : repositorioUsuarios) {
            if (usuario.ID == ID) {
                return usuario;
            }
        }

        throw new NoSuchElementException("Livro não encontrado");
        // return null;
    }

    public Usuario[] Listar() {
        int indice = 0;
        Usuario[] lista = new Usuario[repositorioUsuarios.Tamanho()];

        for (var usuario : repositorioUsuarios) {
            lista[indice] = usuario;
            indice++;
        }

        return lista;

    }

    public Boolean Editar(String stringID, String nome, String cpf) {

        int ID = validaID(stringID);
        for (var usuario : repositorioUsuarios) {

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

        for (var usuario : repositorioUsuarios) {

            if (usuario.ID == ID) {
                Remover(indice);
                return;
            }
            indice++;
        }

        throw new NoSuchElementException("Livro não encontrado");

    }

    public void Remover(int indice) {
        repositorioUsuarios.Remover(indice);
    }

    private int validaID(String stringID) {

        try {
            return Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }
}
