package Adaptadores.Repositorios.EmMemoria;

import Dominio.Modelos.Usuario;
import Aplicacao.Interfaces.IRepositorio;
import Dominio.EstruturasDeDados.Listas.Lista;

public class RepositorioUsuarios extends Lista<Usuario> implements IRepositorio<Usuario> {

}
