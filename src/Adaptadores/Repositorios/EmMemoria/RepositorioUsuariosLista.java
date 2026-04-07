package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;
import Dominio.Modelos.Usuario;
import Aplicacao.Interfaces.IRepositorioUsuario;
import Dominio.EstruturasDeDados.Listas.Lista;

public class RepositorioUsuariosLista implements IRepositorioUsuario {
    protected final Lista<Usuario> lista;

    public RepositorioUsuariosLista() {
        lista = new Lista<>();
    }

    public int Inserir(Usuario dado) {
        return lista.Inserir(dado);
    }

    // retira proximo item da fila
    public Usuario Retirar() {
        return lista.Retirar();
    }

    // retira último item da fila
    public Usuario Ultimo() {
        return lista.Ultimo();
    }

    // visualiza proximo item sem remover
    public Usuario Topo() {
        return lista.Topo();
    }

    // remove item por indice
    public void Remover(int indice) {
        lista.Remover(indice);
    }

    public int Tamanho() {
        return lista.Tamanho();
    }

    public String toString() {
        return lista.toString();
    }

    // percorre a fila sem remover
    @Override
    public Iterator<Usuario> iterator() {
        return lista.iterator();
    }
}
