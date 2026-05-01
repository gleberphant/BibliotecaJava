Olá, pessoal! 

Passando para alinhar um ponto importante sobre a Atividade Somativa 2.

O projeto é cumulativo. Isso significa que vocês devem partir do que já foi desenvolvido até a semana 4 (AS1) e continuar evoluindo o sistema com as implementações das semanas seguintes.

Na prática, o trabalho final deve contemplar:

- Estrutura base do sistema (catálogo, lista, etc.)
- Fila (Queue) e histórico (Stack)
- Grafo de recomendações (semana 4)
- Árvore binária (semana 5)
- Algoritmos de ordenação (semana 6)
- BFS e DFS (semana 7)
- Dijkstra aplicado ao grafo (semana 8)

O Dijkstra não substitui o grafo — ele é uma evolução dele.

Importante: não é necessário “trocar” uma estrutura por outra. O ideal é integrar tudo em um único sistema, por exemplo, organizando as funcionalidades em um menu.

Sugestão de organização:

1 - Catálogo
2 - Fila e histórico
3 - Recomendações (grafo)
4 - Busca em árvore
5 - Ordenações
6 - BFS / DFS
7 - Dijkstra

Também não há problema em ter mais de um algoritmo para a mesma finalidade (ex: diferentes ordenações). Isso pode ser apresentado como opções no sistema.

Por fim, o código deve estar funcional, permitindo testar essas funcionalidades de forma integrada.

Bons estudos e bom desenvolvimento!

Prof.-Tutor Guilherme

-----------------------



O que você deve desenvolver?
Implementação do algoritmo de Dijkstra: use Java para implementar o algoritmo de Dijkstra, adaptando-o para funcionar com um grafo de livros que criamos na atividade somativa da semana 4.

Determinação de caminhos relevantes: aplique o algoritmo para encontrar os caminhos mais curtos no grafo, que podem indicar recomendações interessantes para os usuários com base em suas preferências e interesses.

Aplicação prática: integre essa funcionalidade ao sistema de biblioteca virtual para oferecer mostrar quais são os livros com a menor distância para um livro qualquer. Quanto menor a distância, mais próximo este livro é, e maior é a chance de ser uma boa recomendação. Use o algoritmo a seguir como base para o seu trabalho:

public static Map<Livro, Integer> djikstraSimples(HashMap<Livro, Set<Livro>> grafo, Livro origem) {

    Map<Livro, Integer> distancias = new HashMap<>();

    Queue<Livro> fila = new LinkedList<>();

 

    distancias.put(origem, 0); // como não temos pesos entre os nós, o peso padrão é 0

    fila.add(origem);

 

    while (!fila.isEmpty()) {

        Livro atual = fila.poll();

        int distanciaAtual = distancias.get(atual);

 

        for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {

            if (!distancias.containsKey(vizinho)) {

                distancias.put(vizinho, distanciaAtual + 1);

                fila.add(vizinho);

            }

        }

    }

    return distancias;

}

O que eu devo entregar?
Precisaremos do código-fonte do seu projeto dentro de uma pasta zipada. Isso significa que precisaremos de todos os arquivos com final .java que você criou. Os arquivos com final .class não devem ser incluídos.