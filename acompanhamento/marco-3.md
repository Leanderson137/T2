# Marco 3 — Estratégia Algorítmica

## 1. Propriedade estrutural

O problema pode ser modelado como um **grafo não direcionado**, em que cada aluno representa um vértice e cada amizade representa uma aresta.

A condição do problema exige que dois alunos amigos não pertençam à mesma equipe. Isso corresponde exatamente à definição de um **grafo bipartido**: os vértices podem ser divididos em dois conjuntos de modo que toda aresta tenha uma extremidade em cada conjunto.

## 2. Critério de reconhecimento

A propriedade será reconhecida por meio de uma **2-coloração** do grafo.

Cada vértice receberá uma de duas cores. Ao visitar um vértice, seus vizinhos ainda não coloridos receberão a cor oposta.

O critério utilizado será:

```text
para toda aresta (u, v):
    cor[u] != cor[v]
```

Caso dois vértices adjacentes recebam a mesma cor, o grafo não é bipartido e a resposta será `IMPOSSIBLE`.

Uma caracterização equivalente é que um grafo é bipartido se, e somente se, não possui ciclo de comprimento ímpar.

## 3. Implementações de referência do algs4

### 3.1 `Graph`

`Graph` será utilizada como referência para representar o grafo por **listas de adjacência**.

No problema:

```text
aluno   → vértice
amizade → aresta
```

A representação por listas de adjacência permite percorrer os amigos de cada aluno.

### 3.2 `BipartiteX`

`BipartiteX` será a principal referência para a estratégia de reconhecimento da bipartição.

A classe utiliza **BFS** e alternância de cores entre vértices adjacentes. Ela também considera componentes desconectados.

Essa implementação foi escolhida porque sua estratégia corresponde diretamente à propriedade estrutural do problema.

## 4. Adaptações previstas

### 4.1 Numeração dos vértices

O CSES utiliza alunos numerados de `1` até `n`, enquanto a implementação utiliza vértices de `0` até `n - 1`.

Será realizada a conversão:

```text
aluno 1 → vértice 0
aluno 2 → vértice 1
...
aluno n → vértice n-1
```

### 4.2 Conversão das cores

As duas cores utilizadas pela bipartição serão associadas às equipes:

```text
false → equipe 1
true  → equipe 2
```

### 4.3 Componentes desconectados

O grafo pode possuir vários componentes. Por isso, a busca deverá ser iniciada em todo vértice que ainda não tenha sido visitado.

### 4.4 Formato da saída

Quando o grafo não for bipartido, a saída será:

```text
IMPOSSIBLE
```

Caso contrário, serão impressas as duas equipes, uma para cada aluno, na ordem `1` até `n`.

## 5. Rastreamento manual

Será utilizado o caso:

```text
5 3
1 2
1 3
4 5
```

Representação:

```text
1 ----- 2
|
3

4 ----- 5
```

Estado inicial:

```text
equipes = [0, 0, 0, 0, 0]
fila = []
```

O aluno 1 inicia a primeira busca:

```text
aluno 1 → equipe 1
fila = [1]
```

Ao processar o aluno 1, seus vizinhos 2 e 3 recebem a equipe oposta:

```text
aluno 2 → equipe 2
aluno 3 → equipe 2
fila = [2, 3]
```

Os alunos 2 e 3 são processados sem encontrar conflito.

O próximo aluno ainda não visitado é o 4:

```text
aluno 4 → equipe 1
aluno 5 → equipe 2
```

Resultado:

```text
1 2 2 1 2
```

Todas as amizades ligam alunos de equipes diferentes.

## 6. Complexidade

Considere:

```text
V = n
E = m
```

A representação por listas de adjacência utiliza:

```text
O(V + E)
```

de memória.

A BFS percorre os vértices e as arestas em:

```text
O(V + E)
```

tempo.

Portanto:

```text
Tempo: O(n + m)
```

```text
Memória do grafo: O(n + m)
Memória auxiliar: O(n)
```

## 7. Justificativa da estratégia

A verificação de bipartição por BFS foi escolhida porque o objetivo do problema é exatamente dividir os vértices em dois conjuntos sem que uma aresta ligue dois vértices do mesmo conjunto.

A BFS permite realizar essa 2-coloração de forma direta e também evita depender de recursão profunda para entradas com até `100000` alunos.
