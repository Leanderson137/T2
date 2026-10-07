# Marco 1 — Problema e Conhecimento Prévio

## 1. Resumo do problema

O problema **Building Teams (CSES 1668)** apresenta uma turma com `n` alunos e `m` amizades. O objetivo é dividir os alunos em duas equipes de forma que dois alunos que são amigos não pertençam à mesma equipe.

### Entrada

A primeira linha contém:

```text
n m
```

onde:

- `n` = número de alunos;
- `m` = número de amizades.

As próximas `m` linhas contêm dois números `a` e `b`, indicando que os alunos `a` e `b` são amigos.

### Saída

Para cada aluno, deve ser impresso:

```text
1
```

ou

```text
2
```

indicando a equipe à qual ele pertence.

Se não existir uma divisão válida, deve ser impresso:

```text
IMPOSSIBLE
```

### Restrições

```text
1 ≤ n ≤ 10^5
1 ≤ m ≤ 2 · 10^5
1 ≤ a,b ≤ n
```

Cada amizade ocorre entre dois alunos diferentes e não há mais de uma amizade entre o mesmo par de alunos.

---

## 2. Modelagem do problema

O problema pode ser representado por um **grafo não direcionado**.

A correspondência utilizada é:

```text
aluno   → vértice
amizade → aresta
```

Como a amizade é recíproca, a aresta não possui direção.

A divisão em duas equipes pode ser interpretada como uma divisão dos vértices em dois grupos, de modo que nenhuma aresta conecte dois vértices do mesmo grupo.

---

## 3. Classificação do grafo

O grafo utilizado no problema pode ser classificado como:

- **não direcionado**, pois as amizades são recíprocas;
- **simples**, pois não existem laços e há no máximo uma aresta entre dois vértices;
- **não ponderado**, pois as amizades não possuem peso ou custo;
- **possivelmente desconexo**, pois podem existir grupos de alunos sem ligação com os demais.

---

## 4. Resultado de aprendizagem aferido

O problema permite aferir o conhecimento sobre:

- modelagem de situações reais utilizando grafos;
- identificação de vértices e arestas;
- classificação de grafos;
- uso de estruturas de dados para percorrer grafos;
- aplicação de DFS e BFS em problemas de conectividade e restrições entre vértices.

---

## 5. Participação de DFS e BFS

As buscas **DFS (Depth-First Search)** e **BFS (Breadth-First Search)** são técnicas de percurso de grafos.

Nesse problema, uma busca pode ser utilizada para percorrer os alunos e suas amizades, propagando informações entre vértices relacionados.

A ideia é que, ao visitar um aluno, seus vizinhos sejam processados de acordo com a restrição de pertencerem a equipes diferentes.

A BFS ou a DFS também pode ser iniciada novamente em vértices ainda não visitados para considerar diferentes componentes do grafo.

Na estratégia escolhida para os próximos marcos, será utilizada **BFS com 2-coloração** para verificar a possibilidade de formar as duas equipes.

---

## 6. Instância pequena

Foi criada a seguinte instância para representar o problema:

```text
5 3
1 2
1 3
4 5
```

A interpretação é:

```text
5 alunos
3 amizades
```

As amizades são:

```text
1 -- 2
1 -- 3
4 -- 5
```

Uma representação visual é:

```text
1 ----- 2
|
|
3

4 ----- 5
```

Uma possível divisão em equipes é:

```text
Equipe 1: 1, 4
Equipe 2: 2, 3, 5
```

Correspondendo à saída:

```text
1 2 2 1 2
```

Nessa divisão, todos os pares de amigos estão em equipes diferentes.

---

## 7. Rastreamento inicial

Considerando a instância:

```text
5 3
1 2
1 3
4 5
```

podemos iniciar a busca pelo aluno `1`.

Se o aluno `1` receber a primeira equipe, seus vizinhos `2` e `3` deverão receber a equipe diferente.

O segundo grupo de alunos é formado por `4` e `5`, que também precisam ficar em equipes diferentes.

Assim, uma possível atribuição é:

```text
1 → equipe 1
2 → equipe 2
3 → equipe 2
4 → equipe 1
5 → equipe 2
```

Resultado:

```text
1 2 2 1 2
```

---

## 8. Conclusão

O problema pode ser modelado naturalmente como um grafo não direcionado de amizades.

A condição de que amigos não podem permanecer na mesma equipe indica a necessidade de dividir os vértices em dois grupos compatíveis com as arestas.

A partir dessa modelagem, DFS e BFS podem ser utilizadas para percorrer o grafo. A estratégia que será aprofundada nos próximos marcos é a verificação de uma **2-coloração por BFS**.
