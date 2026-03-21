package Main;

import Handlers.*;

import Repositories.*;
import Usecases.*;

public class Main {

    public static void main(String[] args) {

    

        // configura aplicação
        HandlerCLI app = new HandlerCLI(new ServicoLivro(new RepositorioLivro()));

        // mocar dados
        // Livro[] livrosMocados = {
        // new Livro("Livro 1", "Autor 1", 1),
        // new Livro("Livro 2", "Autor 2", 2),
        // new Livro("Livro 3", "Autor 3", 3),
        // };

        // roda aplicação
        app.Run();

    }

}
