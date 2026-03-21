package Handlers;

import Models.Livro;
import Usecases.IServico;

public class HandlerCLI implements IHandler {

    public IServico servico;

    public HandlerCLI(IServico servico) {
        this.Config(servico);
    }

    private void Config(IServico servico) {
        this.servico = servico;
    }

    public void Run() {

        // loop principal
        while (true) {
            int select = Home();

            switch (select) {
                case 1:
                    AdicionarLivro();
                    break;
                case 2:
                    VisualizarLivro();
                    break;
                case 3:
                    ListarLivros();
                    break;
                case 0: // Opção explícita de saída
                    Sair();
                    return; // Quebra o loop e sai do método
                default:
                    // Tratamento de opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public int Home() {
        System.out.println("Biblioteca de livros");
        System.out.println(".......... MENU ..........");
        System.out.println(". -> Adicionar um livro   ");
        System.out.println(". -> Visualizar um  livro ");
        System.out.println(". -> Lista todos livros   ");
        System.out.println("..........................");

        int select = 0;

        return select;
    }

    public void AdicionarLivro() {

        System.out.println("Informe os dados do livro");

        String titulo = "Livro ";// ler titulo
        String autor = "Autor ";// ler autor
        int ano = 2026;// ler ano

        servico.AdicionarLivro(new Livro(titulo, autor, ano));

    }

    public void EditarLivro() {

        System.out.println("Informe o ID do livro para editar");

        int id = 0; // ler dados

        Livro livroEditado = servico.VisualizarLivro(id);

        System.out.println("Informe os novos dados do livro");

        livroEditado.titulo = "Novo Livro ";// ler titulo
        livroEditado.autor = "Novo Autor ";// ler autor
        livroEditado.ano = 0;// ler ano

        servico.EditarLivro(livroEditado);

    }

    public void VisualizarLivro() {

        System.out.println("Informe o ID do livro para pesquisar");

        int id = 0; // ler dados

        Livro livro = servico.VisualizarLivro(id);

        System.out.println("Visualizando o livro > " + id);
        System.out.println(livro);
    }

    public void ListarLivros() {

        System.out.println("Listando todos os livros");
        Livro[] livros = servico.ListarLivros();

        for (Livro livro : livros) {
            System.out.println(" ** " + livro.toString() + " ");
        }

    }

    public void RemoverLivro() {

        System.out.println("Informe o ID do livro para remover");

        int id = 0; // ler dados

        servico.RemoverLivro(id);

        System.out.println("Removendo o livro > " + id);

    }

    public void Sair() {
        System.out.println("Saindo da Aplicação");

    }
}