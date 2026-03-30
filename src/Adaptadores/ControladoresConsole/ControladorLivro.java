package Adaptadores.ControladoresConsole;

import java.util.Scanner;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;
import Dominio.Modelos.Livro;

public class ControladorLivro implements IControlador {
    public IServico<Livro> servico;
    private final Scanner scanner;

    public ControladorLivro(IServico<Livro> servico, Scanner scanner) {
        this.scanner = scanner;
        this.servico = servico;
    }

    public void Adicionar() {

        System.out.println("Informe os dados do livro");

        System.out.print("Digite nome do livro: ");
        String titulo = scanner.nextLine();// ler titulo
        System.out.print("Digite autor do livro: ");
        String autor = scanner.nextLine();
        ;// ler autor

        int ano = 2026;// ler ano

        servico.Adicionar(new Livro(titulo, autor, ano));

    }

    public void Editar() {

        System.out.println("Informe o ID do livro para editar");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        Livro livroEditado = servico.Visualizar(id);

        System.out.println("Informe os novos dados do livro");

        livroEditado.Titulo = "Novo Livro ";// ler titulo
        livroEditado.Autor = "Novo Autor ";// ler autor
        livroEditado.Ano = 0;// ler ano

        servico.Editar(livroEditado);

    }

    public void Visualizar() {

        System.out.println("Informe o ID do livro para pesquisar");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        Livro livro = servico.Visualizar(id);

        System.out.println("Visualizando o livro > " + id);
        System.out.println(livro);
    }

    public void Listar() {

        System.out.println("Listando todos os livros");
        Livro[] livros = servico.Listar();

        for (Livro livro : livros) {
            System.out.println(" ** " + livro.toString() + " ");
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do livro para remover");

        int id = Integer.parseInt(scanner.nextLine()); // ler dados

        servico.Remover(id);

        System.out.println("Removendo o livro > " + id);

    }

}