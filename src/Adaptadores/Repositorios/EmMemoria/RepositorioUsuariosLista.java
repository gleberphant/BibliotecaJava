package Adaptadores.Repositorios.EmMemoria;

import java.util.Iterator;

import Dominio.Modelos.Usuario;
import Infraestrutura.EstruturasDeDados.Listas.Lista;
import Aplicacao.Interfaces.IRepositorioUsuario;

public class RepositorioUsuariosLista implements IRepositorioUsuario {
    protected final Lista<Usuario> lista;
    int contagem;

    public RepositorioUsuariosLista() {
        lista = new Lista<>();
        contagem = 0;
    }

    public int Contagem() {
        return contagem;
    }

    public int Inserir(Usuario dado) {
        lista.Inserir(dado);
        return contagem++;

    }

    public Lista<Usuario> Listar() {
        return lista;
    }

    public Usuario BuscarID(int ID) {

        for (Usuario usuario : lista) {
            if (usuario.ID == ID) {
                return usuario;
            }
        }

        return null;

    }

    public Usuario BuscarNome(String nome) {
        for (Usuario usuario : lista) {
            if (usuario.Nome.equals(nome)) {
                return usuario;
            }
        }

        return null;
    }

    // remove item por indice
    public boolean Remover(int ID) {
        int indice = 0;

        for (var usuario : lista) {

            if (usuario.ID == ID) {
                lista.Remover(indice);
                return true;
            }
            indice++;
        }
        return false;

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
