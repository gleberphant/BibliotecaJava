package Adaptadores.ControladoresConsole;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;
import Dominio.Modelos.Livro;

public class ControladorLivro implements IControlador {
    public IServico servico;

    public ControladorLivro(IServico servico) {
        this.servico = servico;
    }

    public void Adicionar() {

        System.out.println("Informe os dados do livro");

        String titulo = "Livro ";// ler titulo
        String autor = "Autor ";// ler autor
        int ano = 2026;// ler ano

        servico.AdicionarLivro(new Livro(titulo, autor, ano));

    }

    public void Editar() {

        System.out.println("Informe o ID do livro para editar");

        int id = 0; // ler dados

        Livro livroEditado = servico.VisualizarLivro(id);

        System.out.println("Informe os novos dados do livro");

        livroEditado.Titulo = "Novo Livro ";// ler titulo
        livroEditado.Autor = "Novo Autor ";// ler autor
        livroEditado.Ano = 0;// ler ano

        servico.EditarLivro(livroEditado);

    }

    public void Visualizar() {

        System.out.println("Informe o ID do livro para pesquisar");

        int id = 0; // ler dados

        Livro livro = servico.VisualizarLivro(id);

        System.out.println("Visualizando o livro > " + id);
        System.out.println(livro);
    }

    public void Listar() {

        System.out.println("Listando todos os livros");
        Livro[] livros = servico.ListarLivros();

        for (Livro livro : livros) {
            System.out.println(" ** " + livro.toString() + " ");
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do livro para remover");

        int id = 0; // ler dados

        servico.RemoverLivro(id);

        System.out.println("Removendo o livro > " + id);

    }

}