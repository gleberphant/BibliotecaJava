package Testes;

import java.util.Scanner;

import Adaptadores.ControladoresConsole.*;
import Adaptadores.Repositorios.EmMemoria.RepositorioUsuarios;
import Aplicacao.CasosDeUso.ServicoUsuarios;
import Dominio.Modelos.Usuario;

public class TesteServicoUsuario {

    public static void main(String[] args) {

        System.out.println("REALIZANDO TESTES DAS FUNCIONALDIADES DA APLICAÇÃO");
        // configura aplicação
        ControleUsuario app = new ControleUsuario(
                new ServicoUsuarios(new RepositorioUsuarios()),
                new Scanner(System.in));

        int numItens = 5;

        // mocar dados em massa
        System.out.println("\nMockando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println("ID" + i + "Nome " + i);
            app.servicoUsuarios.Adicionar(new Usuario(i, "Nome ", "cpf"));
        }

        // visualizar
        System.out.println("\nVisualizando Dados");
        for (int i = 0; i < numItens; i++) {
            System.out.println(app.servicoUsuarios.Visualizar(i));
        }

        // remover
        System.out.println("\nRemovendo Dados");
        for (int i = 0; i < numItens - 2; i++) {
            System.out.println("\nRemovendo item " + i);
            app.servicoUsuarios.Remover(i);
        }

        // listar
        System.out.println("\nListar Dados");
        app.Listar();

    }

}
