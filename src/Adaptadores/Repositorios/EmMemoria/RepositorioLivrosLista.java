package Adaptadores.Repositorios.EmMemoria;

import Dominio.Modelos.Livro;
import Aplicacao.Interfaces.IRepositorio;
import Dominio.EstruturasDeDados.Listas.Lista;

public class RepositorioLivrosLista extends Lista<Livro> implements IRepositorio<Livro> {

}
