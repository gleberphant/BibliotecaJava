package Adaptadores.Repositorios.EmMemoria;

import Dominio.Modelos.Usuario;
import Aplicacao.Interfaces.IRepositorioUsuario;
import Dominio.EstruturasDeDados.Listas.Lista;

public class RepositorioUsuariosLista extends Lista<Usuario> implements IRepositorioUsuario<Usuario> {

}
