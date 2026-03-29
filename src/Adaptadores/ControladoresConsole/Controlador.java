package Adaptadores.ControladoresConsole;

import Aplicacao.Interfaces.IControlador;
import Aplicacao.Interfaces.IServico;
import Dominio.Modelos.Livro;

// interface para trabalhar com tipos genericos. objetivo de ter um unico controlador que injeta o servico
public class Controlador implements IControlador {
    public IServico servico;

    public Controlador(IServico servico) {
        this.servico = servico;
    }

    // precisa ser ajustado para tipo generico
    public void Adicionar() {

        System.out.println("Informe os dados do item");

        String titulo = "Livro ";// ler titulo
        String autor = "Autor ";// ler autor
        int ano = 2026;// ler ano

        servico.AdicionarLivro(new Livro(titulo, autor, ano));

    }

    public void Editar() {

        System.out.println("Informe o ID do item para editar");

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

        Livro item = servico.VisualizarLivro(id);

        System.out.println("Visualizando o livro > " + id);
        System.out.println(item);
    }

    public void Listar() {

        System.out.println("Listando todos os livros");
        Livro[] lista = servico.ListarLivros();

        for (Livro item : lista) {
            System.out.println(" ** " + item.toString() + " ");
        }

    }

    public void Remover() {

        System.out.println("Informe o ID do livro para remover");

        int id = 0; // ler dados

        servico.RemoverLivro(id);

        System.out.println("Removendo o livro > " + id);

    }

}