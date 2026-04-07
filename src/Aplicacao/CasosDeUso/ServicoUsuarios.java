package Aplicacao.CasosDeUso;

import java.util.NoSuchElementException;

import Aplicacao.Interfaces.IRepositorioUsuario;
import Dominio.Algoritmos.Criptografia.Encriptador;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ServicoUsuarios {
    IRepositorioUsuario repositorioUsuarios;
    Usuario usuarioLogado;

    public ServicoUsuarios(IRepositorioUsuario repositorio) {
        repositorioUsuarios = repositorio;

    }

    public Usuario Login(String login, String senha) {
        Usuario usuario = BuscarNome(login);

        usuarioLogado = usuario;

        if (usuario.Senha.equals(Encriptador.Encriptar(senha)))
            return usuario;
        else
            return null;
    }

    public Usuario GetUsuarioLogado() {
        return usuarioLogado;
    }

    public int Adicionar(Usuario usuario) {
        // corrigir o ID
        usuario.ID = (repositorioUsuarios.Tamanho() == 0) ? 0 : repositorioUsuarios.Ultimo().ID + 1;

        // encriptar a senha
        usuario.Senha = Encriptador.Encriptar(usuario.Senha);

        repositorioUsuarios.Inserir(usuario);
        return usuario.ID;

    }

    public Usuario Visualizar(String stringID) {

        return BuscarID(stringID);
    }

    private Usuario BuscarID(String stringID) {

        int ID = validaID(stringID);

        if (ID < 0)
            throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

        for (Usuario usuario : repositorioUsuarios) {
            if (usuario.ID == ID) {
                return usuario;
            }
        }

        throw new NoSuchElementException("Usuario não encontrado");
        // return null;
    }

    private Usuario BuscarNome(String nome) {
        for (Usuario usuario : repositorioUsuarios) {
            if (usuario.Nome.equals(nome)) {
                return usuario;
            }
        }

        throw new NoSuchElementException("Usuario não encontrado");
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

    public Boolean Editar(String stringID, String nome, String senha) {

        int ID = validaID(stringID);
        for (var usuario : repositorioUsuarios) {

            if (usuario.ID == ID) {
                usuario.Nome = nome;
                usuario.Senha = Encriptador.Encriptar(senha);
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

    public void RegistrarHistórico(Livro livro) {

        usuarioLogado.historicoNavegacao.Inserir(livro);
        return;
    }

    public Livro[] ListarHistoricoNavegacao(Usuario usuario) {
        int indice = 0;
        Livro[] lista = new Livro[usuario.historicoNavegacao.Tamanho()];

        for (var livro : usuario.historicoNavegacao) {
            lista[indice] = livro;
            indice++;
        }

        return lista;

    }

}
