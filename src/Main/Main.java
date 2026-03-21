public class Main{


    public static void Main(){


        // carregar aplicação

        ViewLivro app = new ViewLivro(
            new ServicoLivro( 
                new RepositorioLivro));




        // looop principal da aplicação e roteamento
        while(true){
            int opt = app.Home();

            switch (opt){
                case 1:
                    app.AdicionarLivro();
                case 2:
                    app.VisualizarLivro();
                case 3:
                    app.ListarLivros();
                default:
                    return;
            }
        }
    }
}
