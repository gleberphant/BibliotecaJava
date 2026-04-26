package Aplicacao.Servicos;

import java.util.NoSuchElementException;

import Aplicacao.Interfaces.IEncriptador;
import Aplicacao.Interfaces.IRepositorioUsuario;
import Dominio.Criptografia.CifraCesar;
import Dominio.EstruturasDeDados.Listas.Lista;
import Dominio.EstruturasDeDados.Listas.Pilha;
import Dominio.Modelos.Livro;
import Dominio.Modelos.Usuario;

public class ServicoUsuarios {
    IRepositorioUsuario repositorioUsuarios;
    Usuario usuarioLogado;

    public ServicoUsuarios(IRepositorioUsuario repositorio) {
        repositorioUsuarios = repositorio;

    }

    public int Adicionar(Usuario usuario) {
        // definir o ID
        usuario.ID = repositorioUsuarios.Contagem();

        // encriptar a senha
        IEncriptador encriptador = new CifraCesar();
        usuario.Senha = encriptador.Encriptar(usuario.Senha);

        repositorioUsuarios.Inserir(usuario);
        return usuario.ID;

    }

    public Usuario Visualizar(String stringID) {

        int ID = validaID(stringID);

        Usuario usuario = repositorioUsuarios.BuscarID(ID);

        if (usuario == null)
            throw new NoSuchElementException("Usuario não encontrado");
        else
            return usuario;
    }

    public Lista<Usuario> Listar() {

        return repositorioUsuarios.Listar();

    }

    public Usuario Editar(Usuario novoUsuario) {

        Usuario usuario = repositorioUsuarios.BuscarID(novoUsuario.ID);

        if (usuario == null)
            throw new NoSuchElementException("Usuario não encontrado");

        usuario.Nome = novoUsuario.Nome;
        usuario.CPF = novoUsuario.CPF;
        usuario.Senha = novoUsuario.Senha;

        return usuario;

    }

    public void Remover(String stringID) {

        int ID = validaID(stringID);

        if (!repositorioUsuarios.Remover(ID))
            throw new NoSuchElementException("Usuario não encontrado");

    }

    public Usuario Login(String login, String senha) {

        Usuario usuario = repositorioUsuarios.BuscarNome(login);

        if (usuario == null)
            throw new NoSuchElementException("Login inválido");

        IEncriptador encriptador = new CifraCesar();

        if (usuario.Senha.equals(encriptador.Encriptar(senha)))
            usuarioLogado = usuario;

        else
            throw new NoSuchElementException("Senha inválida");

        return usuarioLogado;
    }

    public Usuario GetUsuarioLogado() {
        return usuarioLogado;
    }

    private int validaID(String stringID) {

        try {
            int ID = Integer.parseInt(stringID);

            if (ID < 0)
                throw new IllegalArgumentException("ID inválido: deve ser um número positivo.");

            return ID;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID deve ser no formato numérico");
        }
    }

    public void RegistrarHistórico(Livro livro) {

        usuarioLogado.historicoNavegacao.Inserir(livro);
        return;
    }

    public Pilha<Livro> ListarHistoricoNavegacao(String stringID) {
        int ID = validaID(stringID);
        Usuario usuario = repositorioUsuarios.BuscarID(ID);

        if (usuario == null)
            throw new NoSuchElementException("Usuario não encontrado");
        else
            return usuario.historicoNavegacao;

    }

}
