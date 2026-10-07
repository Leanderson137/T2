# Marco 4 — Implementação Final e Conclusão

## 1. Implementação final

Neste marco foi realizada a implementação final da solução para o problema **Building Teams (CSES 1668)**, utilizando a linguagem **Java**.

O problema foi modelado como um grafo não direcionado, em que:

- cada aluno representa um vértice;
- cada amizade representa uma aresta;
- as duas equipes representam as duas partes da bipartição.

A solução utiliza **Busca em Largura (BFS)** para verificar se o grafo é bipartido.

Durante a busca, cada vértice recebe uma de duas cores. Quando um vértice é visitado pela primeira vez, ele recebe a cor oposta à do vértice que o encontrou.

Se dois alunos amigos possuírem a mesma cor, existe um conflito e não é possível dividir os alunos em duas equipes válidas. Nesse caso, a solução imprime:

```text
IMPOSSIBLE
```

Quando não existe conflito, as duas cores são convertidas diretamente para as equipes `1` e `2`.

A implementação final foi organizada em classes separadas:

```text
src/
├── Main.java
└── algs4/
    ├── Graph.java
    ├── Queue.java
    └── BipartiteX.java
```

---

## 2. Implementações de referência utilizadas

Foram utilizadas como referência implementações da biblioteca **algs4**, principalmente `Graph`, `Queue` e `BipartiteX`.

### 2.1 Graph

A classe `Graph` foi utilizada como referência para representar o grafo por meio de listas de adjacência.

No problema:

```text
aluno   → vértice
amizade → aresta
```

Cada amizade é representada como uma aresta não direcionada entre os dois alunos.

### 2.2 Queue

A classe `Queue` foi utilizada como estrutura FIFO para realizar a BFS.

As principais operações utilizadas são:

```text
enqueue → inserir no final da fila
dequeue → remover do início da fila
```

### 2.3 BipartiteX

A classe `BipartiteX` foi a principal referência para o algoritmo.

Sua estratégia de BFS e 2-coloração foi mantida na implementação final.

A lógica utilizada é:

```text
vértice atual → cor X
vizinho        → cor oposta
```

Quando dois vértices adjacentes possuem a mesma cor, o grafo não é bipartido.

---

## 3. Classes reutilizadas e modificadas

### 3.1 Graph

A representação por listas de adjacência foi mantida.

Na adaptação, foi utilizado `ArrayList<Integer>` para armazenar os vizinhos de cada vértice.

### 3.2 Queue

A estrutura FIFO foi mantida para a realização da BFS.

Foram utilizadas as operações:

```text
enqueue
dequeue
isEmpty
```

### 3.3 BipartiteX

A lógica principal da `BipartiteX` foi mantida:

```text
BFS
marcação dos vértices
2-coloração
detecção de conflito
```

Foram removidas funcionalidades que não são necessárias para o problema, como a construção e o armazenamento de um ciclo ímpar.

Essa alteração foi realizada porque o CSES exige somente:

```text
IMPOSSIBLE
```

ou a equipe de cada aluno.

---

## 4. Adaptações realizadas para o problema

### 4.1 Numeração dos alunos

O CSES utiliza alunos numerados de `1` até `n`.

Na implementação, os vértices são numerados de `0` até `n - 1`.

Por isso, durante a leitura:

```java
int a = in.nextInt() - 1;
int b = in.nextInt() - 1;
```

Assim:

```text
aluno 1 → vértice 0
aluno 2 → vértice 1
aluno 3 → vértice 2
...
```

### 4.2 Conversão das cores para equipes

As duas cores utilizadas na bipartição foram convertidas para as equipes do problema:

```text
false → equipe 1
true  → equipe 2
```

A escolha é arbitrária, pois o problema aceita qualquer divisão válida.

### 4.3 Componentes desconectados

O grafo pode possuir vários componentes desconectados.

Por isso, a implementação percorre todos os vértices e inicia uma nova BFS sempre que encontra um vértice que ainda não foi visitado.

Dessa forma, todos os alunos são considerados.

### 4.4 Entrada e saída

Foi utilizado um leitor de entrada com buffer para lidar melhor com entradas grandes.

A resposta é construída utilizando `StringBuilder` e apresenta a equipe de cada aluno na ordem original.

---

## 5. Funcionamento da solução

Foi utilizado o exemplo:

```text
5 3
1 2
1 3
4 5
```

O grafo pode ser representado como:

```text
1 -- 2
|
3

4 -- 5
```

A BFS pode produzir a seguinte atribuição:

```text
Aluno 1 → equipe 1
Aluno 2 → equipe 2
Aluno 3 → equipe 2
Aluno 4 → equipe 1
Aluno 5 → equipe 2
```

Uma saída válida é:

```text
1 2 2 1 2
```

Verificando as amizades:

```text
1 -- 2 → equipes diferentes
1 -- 3 → equipes diferentes
4 -- 5 → equipes diferentes
```

Portanto, a divisão é válida.

---

## 6. Detecção de caso impossível

Foi testado também um grafo com ciclo ímpar:

```text
3 3
1 2
2 3
3 1
```

O grafo forma um triângulo:

```text
    1
   / \
  2---3
```

A busca pode atribuir:

```text
1 → equipe 1
2 → equipe 2
3 → equipe 2
```

Porém, existe uma amizade entre os alunos `2` e `3`.

Como os dois estão na mesma equipe e são amigos, ocorre um conflito.

Nesse caso, a solução imprime:

```text
IMPOSSIBLE
```

---

## 7. Testes realizados

Foram realizados testes no IntelliJ IDEA cobrindo diferentes situações do problema:

| Teste | Situação | Resultado |
|---|---|---|
| 1 | Exemplo oficial | Válido |
| 2 | Caminho simples | Válido |
| 3 | Ciclo par | Válido |
| 4 | Ciclo ímpar | `IMPOSSIBLE` |
| 5 | Componentes desconectados | Válido |
| 6 | Aluno isolado | Válido |
| 7 | Grafo estrela | Válido |
| 8 | Grafo completo | `IMPOSSIBLE` |
| 9 | Componente com ciclo ímpar | `IMPOSSIBLE` |
| 10 | Uma única amizade | Válido |
| 11 | Nenhuma amizade | Válido |
| 12 | Cadeia com vários vértices | Válido |

Todos os testes foram executados com sucesso no IntelliJ IDEA e apresentaram os resultados esperados.

---

## 8. Resultado na plataforma CSES

Após os testes locais, a solução foi submetida à plataforma **CSES**, no problema:

**Building Teams — 1668**

A submissão foi realizada utilizando a linguagem **Java**.

Resultado:

```text
ACCEPTED
```

A evidência da submissão está armazenada em:

```text
evidencias/accepted.png
```

---

## 9. Complexidade

Considerando:

```text
V = n
E = m
```

A representação do grafo utilizando listas de adjacência possui complexidade:

```text
O(V + E)
```

A BFS utilizada para verificar a bipartição também possui complexidade:

```text
O(V + E)
```

Portanto, a complexidade de tempo total é:

```text
O(n + m)
```

A representação do grafo utiliza:

```text
O(n + m)
```

de memória.

As estruturas auxiliares utilizadas pela BFS utilizam:

```text
O(n)
```

de memória.

Resumo:

| Recurso | Complexidade |
|---|---:|
| Tempo total | `O(n + m)` |
| Memória do grafo | `O(n + m)` |
| Memória auxiliar | `O(n)` |

---

## 10. Preparação da apresentação

A apresentação será organizada seguindo a seguinte sequência:

1. apresentação do problema;
2. modelagem do problema como grafo;
3. explicação da propriedade de bipartição;
4. explicação da BFS;
5. atribuição das duas cores;
6. detecção de conflitos;
7. conversão das cores para as equipes;
8. apresentação das implementações de referência utilizadas;
9. apresentação das adaptações realizadas;
10. explicação da complexidade;
11. demonstração de um caso de teste;
12. demonstração do resultado `ACCEPTED` no CSES.

---

## 11. Conclusão

A implementação final resolveu o problema **Building Teams** utilizando a verificação de bipartição por BFS.

As implementações de referência do `algs4` foram utilizadas como base para a representação do grafo, para a estrutura da fila e, principalmente, para a verificação da bipartição.

As classes foram adaptadas para atender ao formato específico do CSES, principalmente em relação à numeração dos alunos, à atribuição das equipes, à entrada e à saída.

A solução foi testada em diferentes cenários no IntelliJ IDEA e posteriormente submetida à plataforma CSES, obtendo:

```text
ACCEPTED
```

Dessa forma, a solução final atende ao problema e conclui a etapa de implementação prevista no Marco 4.
