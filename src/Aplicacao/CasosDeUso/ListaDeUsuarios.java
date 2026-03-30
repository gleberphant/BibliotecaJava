package Aplicacao.CasosDeUso;

import java.util.ArrayList;

import Aplicacao.Interfaces.IRepositorio;
import Aplicacao.Interfaces.IServico;
import Dominio.Modelos.Usuario;

public class ListaDeUsuarios implements IServico<Usuario> {
    ArrayList<Usuario> listaUsuarios;
    IRepositorio repositorio;

    public ListaDeUsuarios(IRepositorio repositorio) {
        listaUsuarios = new ArrayList<Usuario>();
        this.repositorio = repositorio;

    }

    @Override
    public Boolean Adicionar(Usuario novo) {
        novo.ID = (listaUsuarios.size() == 0) ? 0 : listaUsuarios.getLast().ID + 1;
        listaUsuarios.add(novo);
        return true;

    }

    @Override
    public Usuario Visualizar(int ID) {
        for (var usuario : listaUsuarios) {

            if (usuario.ID == ID) {
                return usuario;
            }
        }

        return null;
    }

    @Override
    public Usuario[] Listar() {
        int indice = 0;
        Usuario[] lista = new Usuario[listaUsuarios.size()];

        for (var usuario : listaUsuarios) {
            lista[indice] = usuario;
            indice++;
        }

        return lista;

    }

    @Override
    public Boolean Editar(Usuario novo) {

        int indice = 0;
        for (var usuario : listaUsuarios) {

            if (usuario.ID == novo.ID) {
                listaUsuarios.set(indice, novo);
                break;
            }
            indice++;
        }

        return true;
    }

    @Override
    public Boolean Remover(int ID) {
        int indice = 0;

        for (var usuario : listaUsuarios) {

            if (usuario.ID == ID) {
                listaUsuarios.remove(indice);
                break;
            }

            indice++;
        }

        return true;
    }

}
