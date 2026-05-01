# REQUISITOS FUNCIONAIS


RF01 - LISTA DE LIVROS -    uma lista encadeada para gerenciar os livros na biblioteca. Cada nó da lista deve conter informações como título do livro, autor e ano de publicação


RF02 - FILA DE ESPERA -  Implementar uma fila para gerenciar uma lista de espera de livros (quando um livro está emprestado) e uma pilha para armazenar o histórico de navegação do usuário (último livro visualizado no topo)

RF02 - PILHA DE NAVEGAÇÃO -  Implementar uma pilha para armazenar o histórico de navegação do usuário (último livro visualizado no topo)

RF03 - GRAFO DE RECOMENDAÇÕES - Criar um grafo em que cada nó representa um livro e as arestas representam relações, como livros recomendados. Usar grafos para sugerir livros aos usuários com base em suas leituras anteriores ou interesses

RF04 - arvore binária - Construir uma árvore binária de busca para categorizar livros por diferentes critérios, como gênero ou autor. Facilitar a busca rápida de livros por categoria específica. 

RF05 - Implementar bubble sort e compará-lo com o merge sort para ordenar listas de livros por diferentes critérios.Avaliar a eficiência de diferentes algoritmos de ordenação em listas de livros. 

RF06 - Utilizar DFS e BFS para explorar a árvore binária de livros, permitindo descobrir novas recomendações de leitura ou caminhos entre diferentes gêneros. Melhorar o sistema de recomendação de livros com base nas conexões entre eles. 


RF07 - busca dijkstra -  Aplicar o algoritmo de Dijkstra para encontrar o caminho mais curto ou mais relevante entre diferentes livros ou categorias. Otimizar as recomendações para os usuários, levando em conta não apenas a similaridade, mas também a popularidade ou a avaliação dos livros. 


# REGRAS NEGOCIO

RN01 - quando um usuario visualiza um livro, o livro é adicionado a um historicoNavegacao desse usuário.
RN02 - quando um usuario visualizar um livro, ele deve se conecta a todos usuarios que também visualizaram o mesmo livro



# Sugestão de organização:

1 - Catálogo
2 - Fila e histórico
3 - Recomendações (grafo)
4 - Busca em árvore
5 - Ordenações
6 - BFS / DFS
7 - Dijkstra

Também não há problema em ter mais de um algoritmo para a mesma finalidade (ex: diferentes ordenações). Isso pode ser apresentado como opções no sistema.
