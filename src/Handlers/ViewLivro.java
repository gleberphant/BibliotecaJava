package Handlers;


public class ViewLivro{

    ServicoLivro servico;

    public ViewLivro (IServico servico){
        this.servico = servico;

    }

    public void Home(){
        Sytem.out.println("Biblioteca de livros");
        Sytem.out.println(".......... MENU ..........");
        Sytem.out.println(". -> Adicionar um livro   ");
        Sytem.out.println(". -> Visualizar um  livro ");
        Sytem.out.println(". -> Lista todos livros   ");
        Sytem.out.println("..........................");
    }
    
    public void AdicionarLivro(){
        
        System.out.println("Informe os dados do livro");
        
        String titulo = "NOVO";// ler titulo
        String autor = "AUTOR";// ler autor
        int ano = 2026;// ler ano
                    
        servico.AdicionarLivro(new Livro(titulo, autor, ano))

    }

    public void VisualizarLivro(){
        
        System.out.println("Informe o ID do livro para pesquisar");
        int id = 1;// ler dados 
        
        Livro livro =  servico.VisualizarLivro(id);
        
        System.out.println("Visualizando o livro > "+ id);
        System.out.println(livro);
    }


    public void ListarLivros(){
        System.out.println("Listando todos os livros");
        Livro[] livros =  servico.ListarLivros();

        for (livro : livros){
            System.out.println(livro);
        }
        

    }
}